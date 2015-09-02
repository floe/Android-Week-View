package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import android.bluetooth.*;
import android.bluetooth.le.*;
import android.util.Log;
import android.widget.CheckBox;


/**
 * Created by Raquib-ul-Alam Kanak on 7/21/2014.
 * Website: http://alamkanak.github.io/
 */
public class MainActivity extends ActionBarActivity implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private static final int PICK_DATE_RANGE = 0xF5C4;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private String uid;

    // BTLE stuff
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner btleScanner;
    private BluetoothLeAdvertiser btleAdvertiser;
    private AdvertiseSettings btleAdvSettings;
    private AdvertiseData btleAdvData1;
    private AdvertiseData btleAdvData2;
    private AdvertiseCallback btleAdvCallback;
    private ScanCallback btleScanCallback;
    //private List<ScanFilter> btleFilter;
    //private ScanSettings btleSettings;

    // Calendar stuff
    //private static int event_id = 0;
    private Map<String,List<WeekViewEvent>> btleEvents;
    private EventGenerator eventGenerator;

    List<WeekViewEvent> parse_calendar_cast(int[] raw) {

        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        if (raw.length < 5) return null;

        int start_date   = (raw[0] << 8) | raw[1];                 // days since 1970-01-01
        int start_hour   = raw[3] & 0x1F;                          // first hour of each day
        int end_hour     = (raw[3] >> 5) | ((raw[2] & 0x03) << 3); // last hour of each day
        int slot_length  = (raw[2] >> 2) & 0x03;                   // slot duration (0 = 15 min, 1 = 30 min, 2 = 45 min, 3 = 1h)
        int inc_saturday = (raw[2] >> 4) & 0x01;                   // include saturdays
        int inc_sunday   = (raw[2] >> 5) & 0x01;                   // include sundays

        Calendar start = Calendar.getInstance();
        start.setTimeInMillis( 24*60*60*1000*(long)start_date );
        start.set(Calendar.HOUR_OF_DAY, start_hour);

        Calendar end = (Calendar)start.clone();
        end.set( Calendar.HOUR_OF_DAY, end_hour );

        Log.d("BT","Start: "+start.getTime()+" End: "+end.getTime()+String.format(" slot length: %d Sat: %d Sun: %d", slot_length, inc_saturday, inc_sunday));

        // convert to minutes
        slot_length = (slot_length+1)*15;
        int slots_per_day = (end_hour - start_hour) * 60 / slot_length;
        int ignored_slots = (24*60 / slot_length) - slots_per_day;
        int current_day = 0;
        int slot_duration = 0;

        Log.d("BT",String.format("slot_length: %d, slots_per_day: %d, ignored_slots: %d",slot_length,slots_per_day,ignored_slots));

        // current bit offset (+ 4*8 bits header)
        int i = 0;
        int tmp = 0;

        Calendar current_start = null;

        while (i < (raw.length-4)*8) {
            if (i%8 == 0) tmp = raw[4+(i/8)];
            if (((tmp >> i%8) & 0x01) == 0) {
                // slot has finished, insert event now
                if (slot_duration != 0) {
                    Calendar current_end = (Calendar)current_start.clone();
                    current_end.add(Calendar.MILLISECOND, slot_duration*60*1000);
                    Log.d("BT","Adding event: from "+current_start.getTime()+" to "+current_end.getTime());
                    WeekViewEvent event = new WeekViewEvent(0x4242, "", current_start, current_end);
                    event.setColor(getResources().getColor(R.color.blocked_color));
                    events.add(event);
                }
                slot_duration = 0;
            } else {
                // new slot has started, set start date/time
                if (slot_duration == 0) {
                    current_start = (Calendar)start.clone();
                    current_start.add(Calendar.MILLISECOND, (i + current_day*ignored_slots) * slot_length * 60*1000 );
                }
                slot_duration += slot_length;
            }
            i++;
            if (i%slots_per_day == 0) current_day++;
        }

        return events;
    }

    class myScanCallback extends ScanCallback {
        @Override public void onScanResult(int callbackType, ScanResult result) {

            // check if MSD with ID "CC" is available
            byte[] data = result.getScanRecord().getManufacturerSpecificData(0x4343);
            if (data != null) {

                String addr = result.getDevice().getAddress();
                Log.d("BT","got CC broadcast from "+addr);
                if (btleEvents.containsKey(addr)) return;

                Toast.makeText(MainActivity.this, "Received new CalendarCast from " + result.getDevice().getName(), Toast.LENGTH_LONG).show();

                // get raw data - need to parse on our own
                data = result.getScanRecord().getBytes();
                ArrayList<Byte> map = new ArrayList<Byte>();
                int index = 0;

                // search for MSD blocks (type = 0xFF) and append raw data to ArrayList
                while (index < data.length-1) {
                    byte len  = data[index];
                    byte type = data[index+1];
                    if (type == (byte)0xFF)
                        // 4 bytes can be skipped (length + type + 2 bytes MID)
                        for (int i = index + 4; i < index + len + 1; i++)
                            map.add(data[i]);
                    // continue with next block
                    index += len+1;
                }

                // Log raw data for debugging
                StringBuilder sb = new StringBuilder();
                int[] tmp = new int[map.size()];
                int i = 0;
                for (Byte b : map) {
                    sb.append(String.format("%02X ", b.byteValue()));
                    tmp[i++] = b.byteValue() & 0xFF;
                }
                Log.d("BT",String.format("length: %d, data: ",map.size()) + sb.toString());

                btleEvents.put(addr, parse_calendar_cast(tmp));
                mWeekView.notifyDatasetChanged();
            }
        }

        @Override public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult sr: results)
                onScanResult(0, sr);
        }
    }

    class myAdvertisingCallback extends AdvertiseCallback {
        @Override public void onStartFailure(int errorCode) {
            Log.d("BT", String.format("advertising failed: %d",errorCode));
        }
        @Override public void onStartSuccess(AdvertiseSettings settings) {
            Log.d("BT", "advertising started");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        eventGenerator = new EventGenerator();
        mWeekView.goToHour(8.00);

        Switch mySwitch = (Switch)findViewById(R.id.toggleButton);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((MainActivity)buttonView.getContext()).onButtonToggle(isChecked);
            }
        });

        // get UID for differentiating devices (last digit == 0?)
        uid = ((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        Log.d("Core", "UID = " + uid);

        // init BTLE
        bluetoothAdapter = ((android.bluetooth.BluetoothManager)getSystemService(BLUETOOTH_SERVICE)).getAdapter();

        /* btleFilter = new ArrayList<ScanFilter>();
        btleFilter.add( new ScanFilter.Builder().setManufacturerData(0x4343,null).build() );
        btleSettings = new ScanSettings.Builder().setReportDelay(500).setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build(); */

        btleScanner = bluetoothAdapter.getBluetoothLeScanner();
        btleAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();

        btleScanCallback = new myScanCallback();
        btleAdvCallback = new myAdvertisingCallback();

        btleEvents = new HashMap<String,List<WeekViewEvent>>();
    }

    public void setupAdvertising( Calendar startdate, int starttime, int endtime, int flags ) {
        Log.d("Intent",startdate.toString());
        btleAdvSettings = new AdvertiseSettings.Builder().
                setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY).
                setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH).
                setConnectable(false).
                setTimeout(0).
                build();

        // TODO: build from calendar data
        byte[] rawAdvData1 = {
                0x41, 0x49, // 16713 days since 1970-01-01 = 2015-10-05
                0x16, 0x49, // start at 9:00, end at 18:00, slot length 30 min, include saturdays
                0x03, 0x04, 0x05, 0x06, 0x07 };
        byte[] rawAdvData2 = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
        btleAdvData1 = new AdvertiseData.Builder().setIncludeDeviceName( true).setIncludeTxPowerLevel(false).addManufacturerData(0x4343,rawAdvData1).build();
        btleAdvData2 = new AdvertiseData.Builder().setIncludeDeviceName(false).setIncludeTxPowerLevel(false).addManufacturerData(0x4343,rawAdvData2).build();
    }

    public void startBTLE() {
        //((Switch)findViewById(R.id.toggleButton)).setChecked(true);
        btleAdvertiser.startAdvertising(btleAdvSettings, btleAdvData1, btleAdvData2, btleAdvCallback);
        btleScanner.startScan(btleScanCallback);
        //btleScanner.startScan( btleFilter, btleSettings, new myScanCallback() );
        Log.d("BT", "scanning started");
        Toast.makeText(MainActivity.this, "CalendarCast started.", Toast.LENGTH_LONG).show();
    }

    public void stopBTLE() {
        //((Switch)findViewById(R.id.toggleButton)).setChecked(false);
        if (btleAdvertiser != null) btleAdvertiser.stopAdvertising(btleAdvCallback);
        Log.d("BT", "advertising stopped");
        if (btleScanner != null) btleScanner.stopScan(btleScanCallback);
        Log.d("BT", "scanning stopped");
    }

    @Override
    public void onPause() {
        super.onPause();
        stopBTLE();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK || requestCode != PICK_DATE_RANGE) {
            stopBTLE();
            return;
        }

        Log.d("Intent", data.getLongExtra("startdate",0) + " " + data.getIntExtra("starttime",0) + " " + data.getIntExtra("endtime",0));
        Calendar startdate = Calendar.getInstance();
        startdate.setTimeInMillis(data.getLongExtra("startdate",0));
        setupAdvertising(startdate, data.getIntExtra("starttime", 480), data.getIntExtra("endtime", 1080), data.getIntExtra("flags", 0));
        startBTLE();
    }

    // callback for the toggle button
    public void onButtonToggle(boolean isChecked) {
        Log.d("UI", String.format("button state: %b", isChecked));
        if (isChecked) {
            Intent intent = new Intent(this, DaterangeActivity.class);
            startActivityForResult(intent, PICK_DATE_RANGE);
        } else {
            stopBTLE();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                mWeekView.goToHour(8.00);
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);
                    mWeekView.goToHour(8.00);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);
                    mWeekView.goToHour(8.00);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);
                    mWeekView.goToHour(8.00);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d.M.", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return String.format("%02d:00", hour);
            }
        });
    }

    public int getFillLevel() { return 1; }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        int user = (uid.endsWith("0")) ? 1 : 2;

        // Populate the week view with some events.
        List<WeekViewEvent> events = eventGenerator.getEvents(newYear, newMonth, user, getFillLevel());
        Log.d("Cal","populating events; count = "+events.size());

        for (Map.Entry<String,List<WeekViewEvent>> event_list: btleEvents.entrySet())
            events.addAll(0,event_list.getValue());

        return events;
    }

    public static String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %d.%d.", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.DAY_OF_MONTH), time.get(Calendar.MONTH)+1);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(MainActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(MainActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }
}

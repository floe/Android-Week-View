package com.alamkanak.weekview.sample;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;

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

    void parse_calendar_cast(int[] raw) {

        if (raw.length < 5) return;

        int start_date   = (raw[0] << 8) | raw[1];                 // days since 1970-01-01
        int start_hour   = raw[3] & 0x1F;                          // first hour of each day
        int end_hour     = (raw[3] >> 5) | ((raw[2] & 0x03) << 3); // last hour of each day
        int slot_length  = (raw[2] >> 2) & 0x03;                   // slot duration (0 = 15 min, 1 = 30 min, 2 = 1h)
        int inc_saturday = (raw[2] >> 4) & 0x01;                   // include saturdays
        int inc_sunday   = (raw[2] >> 5) & 0x01;                   // include sundays

        Calendar start = Calendar.getInstance();
        start.setTimeInMillis( 24*60*60*1000*(long)start_date );
        start.set(Calendar.HOUR_OF_DAY, start_hour);

        Calendar end = (Calendar)start.clone();
        end.set( Calendar.HOUR_OF_DAY, end_hour );

        Log.d("BT","Start: "+start.getTime()+" End: "+end.getTime()+String.format(" slot length: %d Sat: %d Sun: %d", slot_length, inc_saturday, inc_sunday));

        for (int i = 4; i < raw.length; i++) {

        }
    }

    class myScanCallback extends ScanCallback {
        @Override public void onScanResult(int callbackType, ScanResult result) {

            // check if MSD with ID "CC" is available
            byte[] data = result.getScanRecord().getManufacturerSpecificData(0x4343);
            if (data != null) {

                // get raw data - need to parse on our own
                data = result.getScanRecord().getBytes();
                ArrayList<Byte> map = new ArrayList<Byte>();
                int index = 0;

                // search for MSD blocks (type = 0xFF) and append raw data to ArrayList
                while (index < data.length) {
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

                // TODO: parse map
                parse_calendar_cast(tmp);
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

        // init BTLE
        bluetoothAdapter = ((android.bluetooth.BluetoothManager)getSystemService(BLUETOOTH_SERVICE)).getAdapter();

        /* btleFilter = new ArrayList<ScanFilter>();
        btleFilter.add( new ScanFilter.Builder().setManufacturerData(0x4343,null).build() );
        btleSettings = new ScanSettings.Builder().setReportDelay(500).setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build(); */

        btleScanCallback = new myScanCallback();

        btleScanner = bluetoothAdapter.getBluetoothLeScanner();
        btleAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();

        btleAdvSettings = new AdvertiseSettings.Builder().
                setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER).
                setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH).
                setConnectable(false).
                setTimeout(0).
                build();

        byte[] rawAdvData = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
        btleAdvData1 = new AdvertiseData.Builder().setIncludeDeviceName(true).setIncludeTxPowerLevel(false).addManufacturerData(0x4343,rawAdvData).build();
        btleAdvData2 = new AdvertiseData.Builder().setIncludeDeviceName(false).setIncludeTxPowerLevel(false).addManufacturerData(0x4343,rawAdvData).build();
        btleAdvCallback = new myAdvertisingCallback();
    }

    @Override
    public void onResume() {
        super.onResume();
        btleAdvertiser.startAdvertising(btleAdvSettings, btleAdvData1, btleAdvData2, btleAdvCallback);
        btleScanner.startScan( btleScanCallback );
        //btleScanner.startScan( btleFilter, btleSettings, new myScanCallback() );
        Log.d("BT", "scanning started");
    }

    @Override
    public void onPause() {
        super.onPause();
        btleAdvertiser.stopAdvertising(btleAdvCallback);
        Log.d("BT", "advertising stopped");
        btleScanner.stopScan(btleScanCallback);
        Log.d("BT", "scanning stopped");
    }

    // callback for the toggle button
    public void onButtonToggle(View view) {
        CheckBox cb = (CheckBox)view;
        Log.d("UI",String.format("button state: %b",cb.isChecked()));
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
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

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
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        return events;
    }

    private String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
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

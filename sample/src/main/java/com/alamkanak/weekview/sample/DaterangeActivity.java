package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.GregorianCalendar;
import com.alamkanak.weekview.sample.R;


public class DaterangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daterange);

        TimePicker tp1 = (TimePicker)findViewById(R.id.timepicker ); tp1.setIs24HourView(true); tp1.setCurrentHour( 8); tp1.setCurrentMinute(0);
        TimePicker tp2 = (TimePicker)findViewById(R.id.timepicker2); tp2.setIs24HourView(true); tp2.setCurrentHour(18); tp2.setCurrentMinute(0);
    }

    public void onOK(View view) {
        DatePicker dp  = (DatePicker)findViewById(R.id.datePicker );
        TimePicker tp1 = (TimePicker)findViewById(R.id.timepicker );
        TimePicker tp2 = (TimePicker)findViewById(R.id.timepicker2);
        Intent result = new Intent();
        result.putExtra("startdate",new GregorianCalendar(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()).getTimeInMillis());
        result.putExtra("starttime",tp1.getCurrentHour()*60+tp1.getCurrentMinute());
        result.putExtra(  "endtime",tp2.getCurrentHour()*60+tp2.getCurrentMinute());
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void onCancel(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
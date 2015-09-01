package com.alamkanak.weekview.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import com.alamkanak.weekview.sample.R;

public class DaterangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daterange);

        ((TimePicker)findViewById(R.id.timepicker )).setIs24HourView(true);
        ((TimePicker)findViewById(R.id.timepicker2)).setIs24HourView(true);
    }

    public void onOK(View view) {
        Intent result = new Intent();
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void onCancel(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
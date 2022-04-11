package com.example.week6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class ActivityTimePicker extends AppCompatActivity {

    TimePicker timePicker;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);

        timePicker = (TimePicker) findViewById(R.id.timepicker);
        textView = (TextView) findViewById(R.id.textView);

        TimePicker.OnTimeChangedListener mTimeChangedListener = new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                textView.setText(new String().format("hour %d: minute: %d", i ,i1));
            }
        };


        timePicker.setOnTimeChangedListener(mTimeChangedListener);
    }
}
package com.example.week6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class ActivityDatePicker extends AppCompatActivity {
    DatePicker datePicker;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        datePicker = (DatePicker) findViewById(R.id.dataPicker);
        textView = (TextView) findViewById(R.id.textView);

        DatePicker.OnDateChangedListener mDateChangeListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                textView.setText(new String().format("year %d: month:%d, day:%d", i, i1+1, i2));
            }
        };

        datePicker.setOnDateChangedListener(mDateChangeListener);
    }
}
package com.example.week6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        textView = (TextView) findViewById(R.id.textView);

        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        textView.setText(dateFormat.format(currentTime));

        CalendarView.OnDateChangeListener mDateChangeListener = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                textView.setText(new String().format("year: %d month: %d, day: %d", i, i1+1, i2));
            }
        };

        calendarView.setOnDateChangeListener(mDateChangeListener);

    }

    public void chronoStartClick(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setTextColor(Color.rgb(0,0,0));

        chronometer.start();
    }

    public void chronoStopClick(View v){
        chronometer.stop();
        chronometer.setTextColor(Color.rgb(255, 0, 0));
    }
}
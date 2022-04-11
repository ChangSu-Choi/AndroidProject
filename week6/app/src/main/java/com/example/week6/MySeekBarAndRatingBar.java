package com.example.week6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class MySeekBarAndRatingBar extends AppCompatActivity {
    RatingBar ratingBar;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_seek_bar_and_rating_bar);

        ratingBar = findViewById(R.id.ratingBar);
        seekBar = findViewById(R.id.seekBar);

        int maxStar = ratingBar.getNumStars();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("maxStar: "+maxStar);
                System.out.println("stepStar: "+v);
                System.out.println("getMax: "+seekBar.getMax());
                System.out.println((int) ((v/maxStar)*seekBar.getMax()));
                seekBar.setProgress((int) ((v/maxStar)*seekBar.getMax()));
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
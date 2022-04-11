package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        ratingBar = findViewById(R.id.ratingBar);

        ImageView imageView1 = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        imageView1.setImageResource(R.drawable.ramen);
        imageView2.setImageResource(R.drawable.sushi);
        viewFlipper.addView(imageView1);
        viewFlipper.addView(imageView2);

        String nameArray [] = new String[]{"돈부리", "팬케이크", "라멘", "스시"};

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String name = nameArray[viewFlipper.getDisplayedChild()];
                Toast toast = Toast.makeText(getApplicationContext(), name+", 별점:"+v, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void nextBtnClick(View v){
        viewFlipper.showNext();
    }

    public void prevBtnClick(View v){
        viewFlipper.showPrevious();
    }
}
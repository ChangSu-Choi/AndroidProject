package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnReturn = (Button) findViewById(R.id.bt1);
        TextView tvbrand = (TextView) findViewById(R.id.tvbrand);
        TextView tvname = (TextView) findViewById(R.id.tvname);
        TextView tvprice = (TextView) findViewById(R.id.tvprice);
        ImageView iv = (ImageView) findViewById(R.id.iv1);

        Intent inIntent = getIntent();

        final String brand = inIntent.getStringExtra("brand");
        final String title = inIntent.getStringExtra("title");
        final String price = inIntent.getStringExtra("price");
        byte[] byteArray = getIntent().getByteArrayExtra("image");

        tvbrand.setText(brand);
        tvname.setText(title);
        tvprice.setText(price);
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv.setImageBitmap(image);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
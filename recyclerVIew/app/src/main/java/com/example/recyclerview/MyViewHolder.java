package com.example.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

public class MyViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView tvbrand, tvprice;
    ImageView imageView;
    Button btn;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvbrand = (TextView) itemView.findViewById(R.id.itemtextview);
        tvprice = (TextView) itemView.findViewById(R.id.itemprice);
        imageView = (ImageView) itemView.findViewById(R.id.itemimageview);
        btn = (Button) itemView.findViewById(R.id.itembutton);
        view = itemView;
    }
}


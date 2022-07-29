package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private ArrayList<PaintTitle> mDataset;
    public MyAdapter(ArrayList<PaintTitle> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, parent, false);
        Log.d("hwang", "onCreateViewHolder");
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imageView.setImageResource(mDataset.get(position % 3).imageId);
        holder.tvbrand.setText(mDataset.get(position % 3).title);
        holder.tvprice.setText(mDataset.get(position %3).price);

        final int newpos = position%3;
        final Context mycontext = holder.itemView.getContext();

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), Second.class);
                Bitmap sendBitmap = BitmapFactory.decodeResource(holder.view.getResources(), mDataset.get(newpos).imageId);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                intent.putExtra("title",mDataset.get(newpos).title);
                intent.putExtra("brand",mDataset.get(newpos).brand);
                intent.putExtra("price",mDataset.get(newpos).price);
                mycontext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 99; //
    }
}
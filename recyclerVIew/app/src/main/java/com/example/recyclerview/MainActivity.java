package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle);
        mLayoutManager = new LinearLayoutManager(this);  // for general
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter PaintTitle
        ArrayList<PaintTitle> myDataset=new ArrayList();
        myDataset.add(new PaintTitle(R.drawable.food1, "food1", "24000","FILARF"));
        myDataset.add(new PaintTitle(R.drawable.food2, "food2", "9000","HAMBURGER"));
        myDataset.add(new PaintTitle(R.drawable.food3,"food3", "3500","FRIES"));

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}

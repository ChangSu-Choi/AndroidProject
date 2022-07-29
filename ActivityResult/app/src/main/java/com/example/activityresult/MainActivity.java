package com.example.activityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int imgCount = 3;
    Button btnFin;
    int voteCount[] = new int[imgCount];
    TextView tv[] = new TextView[imgCount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 엑티비티");

        for(int i = 0; i<imgCount; i++)
            voteCount[i] = 0;

        ImageView image[] = new ImageView[imgCount];
        Integer imageid[] = {R.id.redImg, R.id.blueImg, R.id.blackImg};

        Integer tvId[] = {R.id.tvRed, R.id.tvBlue, R.id.tvBlack};

        for(int i = 0; i<imgCount;i++){
            final int index;
            index = i;
            image[index] = (ImageView) findViewById(imageid[index]);
            tv[index] = (TextView) findViewById(tvId[index]);

            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    tv[index].setText(String.valueOf(voteCount[index]));
                }
            });
        }

        btnFin = (Button) findViewById(R.id.btnFin);
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("VoteCount",voteCount);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            voteCount = data.getIntArrayExtra("VoteCount2");
            for (int i = 0; i < imgCount; i++)
                tv[i].setText(String.valueOf(voteCount[i]));
        }
    }
}
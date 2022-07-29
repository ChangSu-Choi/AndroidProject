package com.example.week11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    ImageButton btn_play, btn_prev, btn_next;
    SeekBar seekBar;
    TextView tv_current, tv_total;
    ListView lv;

    MediaPlayer mPlayer;
    String selectedMP3;
    boolean PAUSED = true;
    final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MP3");
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.iv1);
        btn_play = findViewById(R.id.btn_play);
        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        seekBar = findViewById(R.id.seekbar);
        tv_current = findViewById(R.id.tv_curr);
        tv_total = findViewById(R.id.tv_total);

        lv = (ListView) findViewById(R.id.lv);



        String songList[] = {"david.mp3", "thankgod.mp3", "thetimegoeson.mp3"};
        String songTitle[] = {"David", "Thank God", "The Time Goes On"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, songTitle);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mPlayer!=null){
                    mPlayer.stop();
                }
                selectedMP3 = songList[i];
                play();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PAUSED == true) {
                    btn_play.setImageResource(R.drawable.pause);
                    PAUSED = false;
                    mPlayer.start();
                }
                else{ //PAUSED == false
                    btn_play.setImageResource(R.drawable.play);
                    PAUSED = true;
                    mPlayer.pause();
                }
                makeThread();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                int i = lv.getCheckedItemPosition();
                if(i==2){
                    selectedMP3 = songList[0];
                    lv.setItemChecked(0, true);
                    lv.setItemChecked(2, false);
                }
                else{
                    selectedMP3 = songList[i+1];
                    lv.setItemChecked(i+1, true);
                    lv.setItemChecked(i, false);
                }
                play();
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                int i = lv.getCheckedItemPosition();
                if(i==0){
                    selectedMP3 = songList[2];
                    lv.setItemChecked(2, true);
                    lv.setItemChecked(0, false);
                }
                else{
                    selectedMP3 = songList[i-1];
                    lv.setItemChecked(i-1, true);
                    lv.setItemChecked(i, false);
                }
                play();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mPlayer.seekTo(i);
                    tv_current.setText(String.format(timeFormat.format(mPlayer.getCurrentPosition())));
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        lv.setAdapter(arrayAdapter);
    }



    void play(){
        seekBar.setProgress(0);
        btn_play.setImageResource(R.drawable.pause);
        PAUSED = false;
        if (selectedMP3.equals("david.mp3")) {
            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.david);
            iv1.setImageResource(R.drawable.bewhy1);

        } else if (selectedMP3.equals("thankgod.mp3")) {
            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.thankgod);
            iv1.setImageResource(R.drawable.bewhy2);

        } else if (selectedMP3.equals("thetimegoeson.mp3")) {
            mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.thetimegoeson);
            iv1.setImageResource(R.drawable.bewhy3);

        }
        tv_total.setText(String.format(timeFormat.format(mPlayer.getDuration())));
        seekBar.setMax(mPlayer.getDuration());

        mPlayer.start();
        makeThread();
    }


    void makeThread(){
        new Thread(){
            public void run(){
                if(mPlayer==null) return;
                while(mPlayer.isPlaying()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(mPlayer.getCurrentPosition());
                            tv_current.setText(String.format
                                    (timeFormat.format(mPlayer.getCurrentPosition())));
                        }
                    });
                    SystemClock.sleep(100);
                }
            }
        }.start();
    }

}
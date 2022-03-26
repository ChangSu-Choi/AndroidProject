package com.example.linearlayoutandwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkboxTwice, checkboxBTS;
    RadioGroup group_name, group_size;
    RadioButton jungkuk, jimin, taehyoung;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkboxTwice = (CheckBox) findViewById(R.id.checkboxTwice);
        checkboxBTS = (CheckBox) findViewById(R.id.checkboxBTS);

        imageView = (ImageView) findViewById(R.id.imageView);

        group_name = (RadioGroup) findViewById(R.id.group_name);
        group_size = (RadioGroup) findViewById(R.id.group_size);

        jungkuk = (RadioButton) findViewById(R.id.radiobuttonJungkuk);
        jimin = (RadioButton) findViewById(R.id.radiobuttonJimin);
        taehyoung = (RadioButton) findViewById(R.id.radiobuttonTaehyoung);

        checkboxTwice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxBTS.setChecked(false);
                jungkuk.setText("모모");
                jimin.setText("사나");
                taehyoung.setText("쯔위");
            }
        });

        checkboxBTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkboxTwice.setChecked(false);
                jungkuk.setText("정국");
                jimin.setText("지민");
                taehyoung.setText("태형");
            }
        });

        group_name.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radiobuttonJungkuk){
                    if (jungkuk.getText().toString().equals("정국")) imageView.setImageResource(R.drawable.jungkuk);
                    else imageView.setImageResource(R.drawable.momo);
                }
                else if(i == R.id.radiobuttonJimin){
                    if (jimin.getText().toString().equals("지민")) imageView.setImageResource(R.drawable.jimin);
                    else imageView.setImageResource(R.drawable.sana);
                }
                else{
                    if (taehyoung.getText().toString().equals("태형")) imageView.setImageResource(R.drawable.taehyoung);
                    else imageView.setImageResource(R.drawable.tyuwi);
                }
            }
        });
    }

//    CompoundButton.OnCheckedChangeListener mCheckListener = new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//            if ((compoundButton.getId() == R.id.checkboxTwice) && checkboxBTS.isChecked()) {
//                checkboxBTS.setChecked(false);
//                jungkuk.setText("모모");
//                jimin.setText("사나");
//                taehyoung.setText("쯔위");
//            }
//            else if ((compoundButton.getId() == R.id.checkboxBTS) && checkboxTwice.isChecked()){
//                checkboxTwice.setChecked(false);
//                jungkuk.setText("정국");
//                jimin.setText("지민");
//                taehyoung.setText("태형");
//            }
//        }
//    };
}
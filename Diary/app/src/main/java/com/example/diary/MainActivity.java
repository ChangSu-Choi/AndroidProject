package com.example.diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite, btnCancel;
    String fileName;
    Switch switch1;
    boolean isLogin = false;
    //        회원가입 = 0, 로그인로그아웃체크 = 1
    int savedOrchecked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        switch1 = (Switch) findViewById(R.id.switch1);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);


        //        dialog를 show하기 위한 메서드
        View dialogView = getLayoutInflater().inflate(R.layout.login_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setView(dialogView).create();
        EditText password = dialogView.findViewById(R.id.password);
        Button confirmBtn = dialogView.findViewById(R.id.confirmBtn);
        Button cancelBtn = dialogView.findViewById(R.id.cancelButton);





//        다이얼로그의 확인 버튼 눌렀을 때
//        잠금삼태일 때는 로그인 기능, 잠금 해제 상태일때는 계정 만들기 기능
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("check"+switch1.isChecked());
                if (!switch1.isChecked()){
                    isLogin = sharedPreferencesCheckPssword(password.getText().toString());
                    if (isLogin){
                        switch1.setChecked(false);
                        switch1.setText("잠금해제");
                    }else{
                        switch1.setChecked(true);
                        switch1.setText("잠금");
                    }
                }
                else{
                    sharedPreferencesSave(password.getText().toString());
                    switch1.setChecked(true);
                    switch1.setText("잠금");
                }
                Log.d("confirmBtn", "passwaord : "+password.getText());
                alertDialog.dismiss();
            }
        });



//        todo cancel을 누르면 계속 off로 되어 있음 -> 초기화 시킴
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch1.setChecked(false);
                password.setHint("password");
                alertDialog.dismiss();
            }
        });


//        잠금 상태일때와 잠금해제 상태일때 캘린더를 눌렀을때 표시되는 것을 표현
        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";

                String str = readDiary(fileName);
                if (switch1.isChecked()){
                    edtDiary.setText("로그인하세요");
                    btnWrite.setEnabled(false);
                } else {
                    edtDiary.setText(str);
                    btnWrite.setEnabled(true);
                }
            }

        });

//        작성한 일기를 저장
        btnWrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e){}
            }
        });

//        스위치를 눌렀을떼 dialog 띄움
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });


    }

    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
        } catch (IOException e){
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }
//        sharedPreferences에 저장하는 메서드
    public void sharedPreferencesSave(String password){
        System.out.println("이거!"+password);
        SharedPreferences settings = getSharedPreferences("password", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("password", password);
        editor.commit();
    }

//    입력한 password가 저장된 password인지 확인하는 메서드
    public boolean sharedPreferencesCheckPssword(String password){
        SharedPreferences setting = getSharedPreferences("password", MODE_PRIVATE);
        String savedPass = setting.getString("password","");
        Log.d("CheckPass","password : "+password+" savedPass: "+savedPass);
        System.out.println("password.equals(savedPass): "+password.equals(savedPass));
        return password.equals(savedPass);
    }
}
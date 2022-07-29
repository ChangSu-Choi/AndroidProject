package com.example.Look_back;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFragment extends Fragment {
    ViewGroup viewGroup;
    EditText diary_text;
    Button add_btn;
    TextView date_text;
    public static SQLiteHelper sqLiteHelper;
    long now = System.currentTimeMillis();
    Date mDate = new Date(now);
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 M월d일(EE)");
    String date = simpleDate.format(mDate);
    AlertDialog dialog;
    String getDiary;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_add,container,false);

        date_text = viewGroup.findViewById(R.id.date_text);
        date_text.setText(date);

        diary_text = viewGroup.findViewById(R.id.diary_text);
        add_btn = viewGroup.findViewById(R.id.add_btn);
        sqLiteHelper = new SQLiteHelper(getActivity(),"DIARY.sqlite",null,1);
            Cursor cursor = sqLiteHelper.getData("SELECT diary FROM DIARY WHERE date= '" +date+"'");
            while (cursor.moveToNext()){
                getDiary = cursor.getString(0);
                if (getDiary == null){
                    add_btn.setText("작성");
                } else {
                    add_btn.setText("수정");
                }
                diary_text.setText(getDiary);
            }

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDiary == null){
                    String diary =  diary_text.getText().toString();
                    if (diary.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                        return;

                    }
                    try {
                        sqLiteHelper.insertDiary(
                                date,
                                diary
                        );
                        Toast.makeText(getContext(),"작성 했습니다",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    String diary =  diary_text.getText().toString();
                    if (diary.equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                        return;

                    }
                    try {
                        sqLiteHelper.updateDiary(
                                diary,
                                date
                        );
                        Toast.makeText(getContext(),"수정 했습니다",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        return viewGroup;
    }
}


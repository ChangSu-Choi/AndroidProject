package com.example.Look_back;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewGroup viewGroup;
    public static SQLiteHelper sqLiteHelper;
    RecyclerView diary_list;
    private List<DiaryModel> diaryList = new ArrayList<DiaryModel>();
    DiaryListAdapter adapter ;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public void onResume(){
        super.onResume();
        getDiary();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);

        sqLiteHelper = new SQLiteHelper(getActivity(),"DIARY.sqlite",null,1);

        diary_list = (RecyclerView) viewGroup.findViewById(R.id.diary_list);
        diary_list.setHasFixedSize(true);

        // 리사이클러뷰의 사이즈 고정
        // LinearLayoutManager: 수평,수직으로 배치시켜주는 레이아웃 매니저.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        diary_list.setLayoutManager(layoutManager);
        return viewGroup;
    }

//    저장된 일기를 최신순 4개 나열함
//    저장되어 있는 일기를 diary_list에 넣어주고 어뎁터에 set함
    private void getDiary() {
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM DIARY ORDER BY date DESC LIMIT 4");
        diaryList.clear();
        while (cursor.moveToNext()){

            String date = cursor.getString(0);
            String diary = cursor.getString(1);

            diaryList.add(new DiaryModel(date,diary));
        }
        adapter = new DiaryListAdapter(getActivity(),diaryList);
        diary_list.setAdapter(adapter);
    }
}


package com.example.Look_back;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListFragment extends Fragment {
    ViewGroup viewGroup;
    public static SQLiteHelper sqLiteHelper;
    RecyclerView diary_list;
    private List<DiaryModel> diaryList = new ArrayList<DiaryModel>();
    DiaryListAdapter adapter;
    EditText search;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public void onResume(){
        super.onResume();
        getDiary();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list,container,false);


        sqLiteHelper = new SQLiteHelper(getActivity(),"DIARY.sqlite",null,1);

        diary_list = (RecyclerView) viewGroup.findViewById(R.id.diary_list);
        diary_list.setHasFixedSize(true);
        //리사이클러뷰의 사이즈 고정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        diary_list.setLayoutManager(layoutManager);

//        검색창: editview
        search = viewGroup.findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.getFilter().filter(searchText);
            }
        });
        return viewGroup;
    }

    private void getDiary() {
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM DIARY ORDER BY date desc");
        diaryList.clear();
        while (cursor.moveToNext()){

            String date = cursor.getString(0);
            String diary = cursor.getString(1);
            Log.d("date",date);
            Log.d("diary",diary);
            diaryList.add(new DiaryModel(date,diary));
        }
        adapter = new DiaryListAdapter(getActivity(),diaryList);
        diary_list.setAdapter(adapter);
    }
}


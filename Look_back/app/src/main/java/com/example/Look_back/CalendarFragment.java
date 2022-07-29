package com.example.Look_back;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Look_back.CalendarClass.OneDayDecorator;
import com.example.Look_back.CalendarClass.SaturdayDecorator;
import com.example.Look_back.CalendarClass.SundayDecorator;
import com.example.Look_back.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class CalendarFragment extends Fragment {
    ViewGroup viewGroup;
    MaterialCalendarView materialCalendarView;
    public static SQLiteHelper sqLiteHelper;
    TextView date_text , diary_text;
    String diary ;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_calendar,container,false);

        sqLiteHelper = new SQLiteHelper(getActivity(),"DIARY.sqlite",null,1);

        date_text = viewGroup.findViewById(R.id.date_text);
        diary_text = viewGroup.findViewById(R.id.diary_text);

        materialCalendarView = viewGroup.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 0, 1))
                .setMaximumDate(CalendarDay.from(2040, 11, 18))
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator()
        );

//        캘린너 날짜를 클릭하면 해당하는 정보를 출력
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                diary_text.setText("기록이 없습니다");
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();
                String shot_Day = Year + "년 " + Month + "월" + Day+"일";
                date_text.setText(shot_Day);
                Cursor cursor = sqLiteHelper.getData("SELECT diary FROM DIARY WHERE date LIKE '%"+shot_Day+"%'");
                while (cursor.moveToNext()){
                    diary = cursor.getString(0);
                    diary_text.setText(diary);
                }

            }
        });

        return viewGroup;
    }




}


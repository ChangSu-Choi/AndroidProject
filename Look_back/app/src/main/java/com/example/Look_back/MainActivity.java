package com.example.Look_back;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static SQLiteHelper sqLiteHelper;
    BottomNavigationView bottomNavigationView;
    AddFragment addFragment;
    CalendarFragment calendarFragment;
    HomeFragment homeFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this,"DIARY.sqlite",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS DIARY(date VARCHAR PRIMARY KEY , diary VARCHAR)");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        addFragment = new AddFragment();
        calendarFragment = new CalendarFragment();
        listFragment = new ListFragment();
        homeFragment = new HomeFragment();


//        getSupportFragmentManager()를 호출하여 FragmentManager를 가져옴.
//        그런 다음, beginTransaction()을 호출하여 FragmentTransaction을 생성하고 replace()를 호출하여 프래그먼트를 교체

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,homeFragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.add:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,addFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.home:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,homeFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.search:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,listFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.calendar:{
                        getSupportFragmentManager().beginTransaction() .replace(R.id.main_layout,calendarFragment).commitAllowingStateLoss();
                        return true;

                    }default: return false;
                }
            }
        });
    }
}
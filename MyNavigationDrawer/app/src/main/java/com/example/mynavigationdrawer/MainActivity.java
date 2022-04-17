package com.example.mynavigationdrawer;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynavigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

//    선택된 이미지의 아이디
    private int img_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(R.id.nav_home == item.getItemId()) {
                    Menu menu = navigationView.getMenu();
                    MenuItem navHome = menu.findItem(R.id.nav_home);

//                    다시 default로
                    if (navHome.getTitle() == "로그아웃") {
                        ImageView oriImage = findViewById(R.id.navHeaderImageView);
                        oriImage.setImageResource(R.mipmap.ic_launcher_round);

                        TextView oriName = findViewById(R.id.navHeaderName);
                        oriName.setText(getString(R.string.nav_header_title));

                        TextView oriEmail = findViewById(R.id.navHeaderEmail);
                        oriEmail.setText(getString(R.string.nav_header_subtitle));

                        navHome.setTitle(getString(R.string.menu_home));
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        옵션메뉴가 클릭 되었을때만
        int id = item.getItemId();
        if(id == R.id.action_settings){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("사용자 입력");

//        Dialog에 View를 세팅
            View dialogview;
            dialogview = View.inflate(this, R.layout.dialogview, null);
            dlg.setView(dialogview);



            final String[] imageName_arr = new String[]{"개", "고양이", "말"};
            dlg.setSingleChoiceItems(imageName_arr, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i == 0) img_id = R.drawable.dog;
                    if(i == 1) img_id = R.drawable.cat;
                    if(i == 2) img_id = R.drawable.horse;
                }
            });


//        Ok를 눌렀을 때 발생하는 이벤트 처리
            dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText name = dialogview.findViewById(R.id.dlgEditTextName);
                    EditText email = dialogview.findViewById(R.id.dlgEditTextEmail);


//                네비게이션헤더에 있는 사진 설정
                    ImageView navHeaderImageView = findViewById(R.id.navHeaderImageView);
                    navHeaderImageView.setImageResource(img_id);
//                네비게이션헤더에 있는 이름 설정
                    TextView navHeaderName = findViewById(R.id.navHeaderName);
                    navHeaderName.setText(name.getText().toString());
//                네비게이션헤도에 있는 이메일 설정
                    TextView navHeaderEmail = findViewById(R.id.navHeaderEmail);
                    navHeaderEmail.setText(email.getText().toString());

//                    네이게이션 메뉴 이름 로그아웃으로 바꾸기
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    Menu menu = navigationView.getMenu();
                    MenuItem navHome = menu.findItem(R.id.nav_home);
                    navHome.setTitle("로그아웃");
                }
            });


            dlg.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
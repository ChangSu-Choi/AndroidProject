package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_STORAGE = 100;
    EditText ed;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);

        checkPermission();



    }

    public void clearText(View v) {
        ed.setText("");
    }

    public void sharedPreferencesSaveClick(View v) {
        SharedPreferences settings = getSharedPreferences("testShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", ed.getText().toString());
        editor.commit();
    }

    public void sharedPreferencesLoadClick(View v) {
        SharedPreferences settings = getSharedPreferences("testShared", MODE_PRIVATE);
        String str = settings.getString("name", "");
        ed.setText(str);
    }

    public void internalSaveClick(View v) {
        try {
            FileOutputStream outFs = openFileOutput("internal.txt", MODE_PRIVATE);
            String str = ed.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void internalLoadClick(View v) {
        try {
            FileInputStream inFs = openFileInput("internal.txt");
//            FileInputStream inFs = new FileInputStream(new File(getFilesDir(),"internal.txt"));
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            ed.setText(new String(txt));
        } catch (IOException e) {
        }
    }

    public void externalSaveClick(View v){
//        String strDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Log.d("Choi", "external storage path="+strDpath);
//        final File myDir = new File(strDpath+"/mydir");

        File myDir = new File(getExternalFilesDir(null).getAbsolutePath()+"/mydir");
        myDir.mkdir();

        Log.d("Choi", myDir.toString());

        try{
            FileOutputStream outFs = new FileOutputStream(new File(myDir, "external.txt"));
            String str = ed.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
            Log.d("Choi","external save ok");
        } catch (IOException e){
            Log.d("Choi", "external save error"+e.toString());
        }
    }

    public void externalLoadClick(View v){
        try{
            File myDir = new File(getExternalFilesDir(null).getAbsolutePath()+"/mydir");
            FileInputStream inFs = new FileInputStream(new File(myDir, "external.txt"));
            byte[] txt = new byte[500];
            inFs.read(txt);
            ed.setText(new String(txt));
        } catch (IOException e){
            Log.d("Choi", "external load error" + e.toString());
        }
    }

    public void copyClick(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String strSDpaht2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        Log.d("Choi", strSDpath+"   public path= "+strSDpaht2);
        try{
            InputStream inFs = getResources().openRawResource(R.raw.donburi);

           // FileOutputStream outFs = new FileOutputStream(new File(strSDpath, "donburi1.jpeg"));
            FileOutputStream outFs2 = new FileOutputStream(new File(strSDpaht2, "donburi2.jpeg"));
            int c;
            while((c = inFs.read()) != -1)
            {
              //  outFs.write(c);
                outFs2.write(c);
            }

           // outFs.close();
            outFs2.close();
            inFs.close();
        } catch (IOException e){
            Log.d("Choi", "error"+e.toString());
        }
    }

    private void checkPermission() {

        //Log.d("hwang","check=" + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);
            // MY_PERMISSION_REQUEST_STORAGE is an
            // app-defined int constant

        } else {
            //Log.e(TAG, "permission deny");
            //writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "Read/Write external storage 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                }

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted,
                    Toast.makeText(this, "권한 승인", Toast.LENGTH_SHORT).show();
                    Log.d("hwang", "Permission granted");

                } else {
                    Log.d("hwang", "Permission deny");
                    // permission denied,
                }
                break;
        }
    }

    public void deleteClick(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(strSDpath, "donburi.jpeg");

        file.delete();
    }

    public void showImageClick(View v){
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(strSDpath, "donburi.jpeg");

        imageView.setImageURI(Uri.fromFile(file));
    }

}
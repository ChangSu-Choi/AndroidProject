package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onBtnClick(View view){
        TextView textViewFirstName = findViewById(R.id.textViewFirstName);
        TextView textViewLastName = findViewById(R.id.textViewLastName);
        TextView textViewEmail = findViewById(R.id.textViewEmail);

        EditText editFirstName = findViewById(R.id.edtFirstName);
        EditText editLastName = findViewById(R.id.edtLastName);
        EditText editEmail = findViewById(R.id.edtEmail);

        textViewFirstName.setText("First Name: " + editFirstName.getText().toString());
        textViewLastName.setText("Last Name: " + editLastName.getText().toString());
        textViewEmail.setText("Email: " + editEmail.getText().toString());

    }


//    public void onBtnClick(View view) {
//
//        TextView txtHello = findViewById(R.id.txtMessage);
//        EditText edtTxtName = findViewById(R.id.edtTxtName);
//
//        txtHello.setText("Hello"+edtTxtName.getText().toString());
//    }
}
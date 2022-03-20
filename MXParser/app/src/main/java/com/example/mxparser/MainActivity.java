package com.example.mxparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view){

        EditText editText = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView);
        String sik = editText.getText().toString();

        Expression ex = new Expression(sik);
        String result = String.valueOf(ex.calculate());

        textView.setText(result);

    }
}
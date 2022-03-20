package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.calculate.parsingCal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView TV;
//    연산자 버튼을 누루기 전까지 string에 숫자 저장
    private String savedString = "";
//    String에 저장된 연산자와 숫자를 분리하여 .add
    private ArrayList<String> splitedString = new ArrayList<>();
    private boolean countEqual = false;
    private boolean countOperator = false;
    private String finalAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

    public void button1Click(View v){
        Button button1 = findViewById(R.id.button1);
        TV=findViewById(R.id.TextView);

        String lang = button1.getText().toString();
        TV.append(lang);

        countOperator = false;
    }

    public void button2Click(View v){
        Button button2 = findViewById(R.id.button2);
        TV=findViewById(R.id.TextView);

        String lang = button2.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button3Click(View v){
        Button button3 = findViewById(R.id.button3);
        TV=findViewById(R.id.TextView);

        String lang = button3.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button4Click(View v){
        Button button4 = findViewById(R.id.button4);
        TV=findViewById(R.id.TextView);

        String lang = button4.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button5Click(View v){
        Button button5 = findViewById(R.id.button5);
        TV=findViewById(R.id.TextView);

        String lang = button5.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button6Click(View v){
        Button button6 = findViewById(R.id.button6);
        TV=findViewById(R.id.TextView);

        String lang = button6.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button7Click(View v){
        Button button7 = findViewById(R.id.button7);
        TV=findViewById(R.id.TextView);

        String lang = button7.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button8Click(View v){
        Button button8 = findViewById(R.id.button8);
        TV=findViewById(R.id.TextView);

        String lang = button8.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button9Click(View v){
        Button button9 = findViewById(R.id.button9);
        TV=findViewById(R.id.TextView);

        String lang = button9.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void button0Click(View v){
        Button button0 =findViewById(R.id.button0);
        TV=findViewById(R.id.TextView);

        String lang = button0.getText().toString();
        savedString += lang;
        TV.append(lang);
        countOperator = false;
    }

    public void buttonPlusClick(View v) {
        Button buttonPlus = findViewById(R.id.buttonPlus);
        TV = findViewById(R.id.TextView);

        // 연산자를 두번 연속 누르면 오류 츌력
        if(countOperator){TV.setText("ERROR"); countOperator = false;};
        // 만약 = 를 누른적이 있다면 청소해줘야 함
        if(countEqual){TV.setText(finalAnswer); countEqual = false;}

        String lang = buttonPlus.getText().toString();
        if(!savedString.equals(""))splitedString.add(savedString);
        splitedString.add("+");
        savedString = "";
        TV.append(lang);

        countOperator = true;
    }

    public void buttonMultiplyClick(View v){
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        TV=findViewById(R.id.TextView);

        // 연산자를 두번 연속 누르면 오류 츌력
        if(countOperator){TV.setText("ERROR"); countOperator = false;};
        // 만약 = 를 누른적이 있다면 청소해줘야 함
        if(countEqual){TV.setText(finalAnswer); countEqual = false;}

        String lang = buttonMultiply.getText().toString();
        if(!savedString.equals(""))splitedString.add(savedString);
        splitedString.add("*");
        savedString = "";
        TV.append(lang);

        countOperator = true;
    }

    public void buttonDivideClick(View v){
        Button buttonDivide =  findViewById(R.id.buttonDivide);
        TV=findViewById(R.id.TextView);

        // 연산자를 두번 연속 누르면 오류 츌력
        if(countOperator){TV.setText("ERROR"); countOperator = false;};
        // 만약 = 를 누른적이 있다면 청소해줘야 함
        if(countEqual){TV.setText(finalAnswer); countEqual = false;}

        String lang = buttonDivide.getText().toString();
        if(!savedString.equals(""))splitedString.add(savedString);
        splitedString.add("/");
        savedString = "";
        TV.append(lang);

        countOperator = true;
    }

    public void buttonSubtractClick(View v){
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        TV=findViewById(R.id.TextView);

        // 연산자를 두번 연속 누르면 오류 츌력
        if(countOperator){TV.setText("ERROR"); countOperator = false;};
        // 만약 = 를 누른적이 있다면 청소해줘야 함
        if(countEqual){TV.setText(finalAnswer); countEqual = false;}

        String lang = buttonSubtract.getText().toString();
        if(!savedString.equals(""))splitedString.add(savedString);
        splitedString.add("-");
        savedString = "";
        TV.append(lang);

        countOperator = true;
    }

    public void buttonClearClick(View v){
        Button buttonClear =  findViewById(R.id.buttonClear);
        TV=findViewById(R.id.TextView);
        splitedString.clear();
        savedString = "";
        TV.setText("");
        countOperator = false;
        countEqual = false;
    }

    public void buttonEqualClick(View v){
        Button buttonEqual =  findViewById(R.id.buttonEqual);
        TV=findViewById(R.id.TextView);
        splitedString.add(savedString);

        parsingCal result = new parsingCal();
        finalAnswer = result.calulation(splitedString);
        TV.append("= "+finalAnswer);
        splitedString.clear();
        savedString = "";
        splitedString.add(finalAnswer);

        countEqual = true;
    }
}
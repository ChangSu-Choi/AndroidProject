package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText ed;
    ArrayList midList;
    ArrayAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.listview);
        ed=(EditText) findViewById(R.id.edittext);

        midList=new ArrayList();
        midList.add("Dexter");
        midList.add("24hours");

        adapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, midList);


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                midList.remove(arg2);
                adapter2.notifyDataSetChanged();
                return false;

            }
        });
        lv.setAdapter(adapter2);

        final String[] midNames = {"로스트", "가십걸", "빅뱅이론"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter spinnerAdapter;

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, midNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                midList.add(midNames[i]);
                adapter2.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView adapterView) {
                Toast.makeText(getApplicationContext(), "nothing selected", Toast.LENGTH_SHORT).show();

            }
        });



//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mid);
//        lv.setAdapter(adapter);
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "Click: "+mid[i]+" arg2 = "+ i+"arg3 = " +l, Toast.LENGTH_SHORT).show();
//            }
//        });

////        single Choice
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mid);
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        lv.setAdapter(adapter);

////        Multiple Choice
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mid);
//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        lv.setAdapter(adapter);\
    }

    public void addClick(View v){
        midList.add(ed.getText().toString());
        adapter2.notifyDataSetChanged();
    }
}
package com.example.lab2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String > cityAdapter;
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;
    Button confirmButton;
    EditText input;
    Boolean add = Boolean.FALSE;
    Boolean delete = Boolean.FALSE;

    int toDeletePOS = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById((R.id.delete_button));
        input = findViewById((R.id.input_city));
        confirmButton = findViewById(R.id.confirm_button);

        String []cities = {"Lahore","Karachi","Peshawar","Quetta"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            add = Boolean.FALSE;
            delete = Boolean.TRUE;
            toDeletePOS = position;
        });

        addButton.setOnClickListener( event -> {
            add = Boolean.TRUE;
            delete = Boolean.FALSE;
                }
        );

        deleteButton.setOnClickListener( event -> {
            if (delete && toDeletePOS>-1) {
                String selectedCity = dataList.get(toDeletePOS);
                dataList.remove(toDeletePOS);
                cityAdapter.notifyDataSetChanged();
            }
            else{
                return;
            }
        }
        );

        confirmButton.setOnClickListener( event -> {
            String city = input.getText().toString();

            if (city.isEmpty()) {
                return;
            }

            if (add) {
                dataList.add((city));
            }
            else{
                return;
            }
            cityAdapter.notifyDataSetChanged();
            input.setText("");
        }

        );



    }
}
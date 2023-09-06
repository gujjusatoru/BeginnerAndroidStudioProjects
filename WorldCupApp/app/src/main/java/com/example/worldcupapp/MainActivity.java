package com.example.worldcupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] Worldcup={
            "India",
            "Pakistan",
            "Sri Lanka",
            "Australia",
            "New Zealand",
            "South Africa",
            "Colombia"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.listview);
        ArrayAdapter adapt= new ArrayAdapter(this, R.layout.list_view1, R.id.textView,  Worldcup);
        list.setAdapter(adapt);
    }
}
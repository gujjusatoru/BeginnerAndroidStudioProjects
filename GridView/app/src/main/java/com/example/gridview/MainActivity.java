package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<ModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.grid);
        list= new ArrayList<ModelClass>();
        list.add(new ModelClass(R.drawable.one,"At a Lake"));
        list.add(new ModelClass(R.drawable.two,"Watching Sunset"));
        list.add(new ModelClass(R.drawable.three,"In a Forest"));
        list.add(new ModelClass(R.drawable.four,"On the Beach"));
        list.add(new ModelClass(R.drawable.five,"Under a Waterfall"));
        list.add(new ModelClass(R.drawable.six,"In a Spring field"));
        list.add(new ModelClass(R.drawable.seven,"In a Bustling City"));
        list.add(new ModelClass(R.drawable.eight,"In Bed"));

        CustomAdapter myAdapter= new CustomAdapter(this, list);
        gridView.setAdapter(myAdapter);
    }

}
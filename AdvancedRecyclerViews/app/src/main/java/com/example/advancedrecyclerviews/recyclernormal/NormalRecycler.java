package com.example.advancedrecyclerviews.recyclernormal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class NormalRecycler extends AppCompatActivity {
    RecyclerView normalRecycler;
    private NormalAdapter adapter;
    private ArrayList<Planet> listPlanets;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_recycler);
        back=findViewById(R.id.normalback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        normalRecycler=findViewById(R.id.recycle1);
        normalRecycler.setLayoutManager(new LinearLayoutManager(this));
        listPlanets=new ArrayList<>();
        adapter=new NormalAdapter(this, listPlanets);
        createListData();
        normalRecycler.setAdapter(adapter);


    }

    private void createListData() {
        listPlanets.add(new Planet("Mercury",R.drawable.mercury,57.9));
        listPlanets.add(new Planet("Venus",R.drawable.venus,108.2));
        listPlanets.add(new Planet("Earth",R.drawable.earth,149.6));
        listPlanets.add(new Planet("Mars",R.drawable.mars,227.9));
        listPlanets.add(new Planet("Jupiter",R.drawable.jupiter,778.6));
        listPlanets.add(new Planet("Saturn",R.drawable.saturn,1433.5));
        listPlanets.add(new Planet("Uranus",R.drawable.uranus,2872.5));
        listPlanets.add(new Planet("Neptune",R.drawable.neptune,4495.1));
        listPlanets.add(new Planet("Pluto",R.drawable.pluto,5900));
    }
}
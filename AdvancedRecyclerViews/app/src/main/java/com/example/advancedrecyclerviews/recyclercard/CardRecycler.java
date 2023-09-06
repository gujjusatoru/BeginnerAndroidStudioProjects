package com.example.advancedrecyclerviews.recyclercard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class CardRecycler extends AppCompatActivity {
    RecyclerView cardRecycler;
    CardAdapter adapter;
    ArrayList<Stones> stonesArrayList;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_recycler);
        back=findViewById(R.id.cardback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cardRecycler=findViewById(R.id.recycle2);
        cardRecycler.setLayoutManager(new LinearLayoutManager(this));
        stonesArrayList=new ArrayList<>();
        addData();
        adapter=new CardAdapter(this,stonesArrayList);
        cardRecycler.setAdapter(adapter);

    }

    private void addData() {
        stonesArrayList.add(new Stones("Amethyst",R.drawable.amethyst));
        stonesArrayList.add(new Stones("Celestine",R.drawable.celestine));
        stonesArrayList.add(new Stones("Citrine",R.drawable.citrine));
        stonesArrayList.add(new Stones("Emerald",R.drawable.emerald));
        stonesArrayList.add(new Stones("Rose Quartz",R.drawable.rose_quartz));
        stonesArrayList.add(new Stones("Hematite",R.drawable.hematite));
        stonesArrayList.add(new Stones("Iron Pyrite",R.drawable.iron_pyrite));
        stonesArrayList.add(new Stones("Tiger Eye",R.drawable.tiger_eye));
    }
}
package com.example.advancedrecyclerviews.recyclersingle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class SingleRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    SingleAdapter adapter;
    ArrayList<Animals> animalsArrayList=new ArrayList<>();
    Button back, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recycler);

        back=findViewById(R.id.singleback);
        show=findViewById(R.id.singleshow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.selected()!=null){
                    Toast.makeText(SingleRecycler.this, ""+adapter.selected().getName(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SingleRecycler.this, "Nothing Selected!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView=findViewById(R.id.recycle3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        enterData();
        adapter=new SingleAdapter(this,animalsArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void enterData() {
        animalsArrayList.add(new Animals("Snake"));
        animalsArrayList.add(new Animals("Rabbit"));
        animalsArrayList.add(new Animals("Cat"));
        animalsArrayList.add(new Animals("Hamster"));
        animalsArrayList.add(new Animals("Turtle"));
        animalsArrayList.add(new Animals("Dog"));
        animalsArrayList.add(new Animals("Llama"));
        animalsArrayList.add(new Animals("Cow"));
        animalsArrayList.add(new Animals("Beetles"));
        animalsArrayList.add(new Animals("Tiger"));
    }
}
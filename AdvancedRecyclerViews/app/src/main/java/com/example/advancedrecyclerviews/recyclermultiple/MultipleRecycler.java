package com.example.advancedrecyclerviews.recyclermultiple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.advancedrecyclerviews.R;
import com.example.advancedrecyclerviews.recyclersingle.Animals;

import java.util.ArrayList;

public class MultipleRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AnimalsMulti> multiArrayList=new ArrayList<>();
    MultiAdapter adapter;
    Button back, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_recycler);
        back=findViewById(R.id.multiback);
        show=findViewById(R.id.multishow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.selected().size()>0){
                    StringBuilder string=new StringBuilder();
                    for(int i=0;i<adapter.selected().size();i++){
                        string.append(adapter.selected().get(i).getName());
                        string.append(" ");
                    }
                    Toast.makeText(MultipleRecycler.this, "Selected: "+string, Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MultipleRecycler.this, "Nothing Selected!", Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView=findViewById(R.id.recycle5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createList();
        adapter=new MultiAdapter(multiArrayList,this);
        recyclerView.setAdapter(adapter);
    }

    private void createList() {
        multiArrayList.add(new AnimalsMulti("Snake"));
        multiArrayList.add(new AnimalsMulti("Rabbit"));
        multiArrayList.add(new AnimalsMulti("Cat"));
        multiArrayList.add(new AnimalsMulti("Hamster"));
        multiArrayList.add(new AnimalsMulti("Turtle"));
        multiArrayList.add(new AnimalsMulti("Dog"));
        multiArrayList.add(new AnimalsMulti("Llama"));
        multiArrayList.add(new AnimalsMulti("Cow"));
        multiArrayList.add(new AnimalsMulti("Beetles"));
        multiArrayList.add(new AnimalsMulti("Tiger"));
    }
}
package com.example.advancedrecyclerviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.advancedrecyclerviews.recyclercard.CardRecycler;
import com.example.advancedrecyclerviews.recyclermanyview.ManyViewRecycler;
import com.example.advancedrecyclerviews.recyclermultiple.MultipleRecycler;
import com.example.advancedrecyclerviews.recyclernormal.NormalRecycler;
import com.example.advancedrecyclerviews.recyclersingle.SingleRecycler;
import com.example.advancedrecyclerviews.recyclerswipe.SwipeRecycler;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.normal);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, NormalRecycler.class);
                startActivity(i);
            }
        });
        btn2=findViewById(R.id.cardview);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, CardRecycler.class);
                startActivity(i);
            }
        });
        btn3=findViewById(R.id.single);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, SingleRecycler.class);
                startActivity(i);
            }
        });
        btn4=findViewById(R.id.manyview);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, ManyViewRecycler.class);
                startActivity(i);
            }
        });
        btn5=findViewById(R.id.multiple);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MultipleRecycler.class);
                startActivity(i);
            }
        });
        btn6=findViewById(R.id.swipe);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, SwipeRecycler.class);
                startActivity(i);
            }
        });
    }
}
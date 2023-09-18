package com.example.travelanimation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travelanimation.R;
import com.example.travelanimation.data.DataModel;

import java.util.ArrayList;
import java.util.Date;

public class LikedActivity extends AppCompatActivity {
    ArrayList<DataModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);
        Intent get=getIntent();
        itemList=new ArrayList<>(get.getParcelableArrayListExtra("liked"));

    }
}
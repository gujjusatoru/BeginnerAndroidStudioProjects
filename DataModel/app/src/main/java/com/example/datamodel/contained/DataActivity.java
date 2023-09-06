package com.example.datamodel.contained;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.datamodel.R;

public class DataActivity extends AppCompatActivity {
    TextView name_tv,email_tv,num_tv,gender_tv,time_tv,date_tv,count_tv;
     UserData set=new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Intent in=getIntent();
        name_tv=findViewById(R.id.name_dis);
        num_tv=findViewById(R.id.number_dis);
        email_tv=findViewById(R.id.email_dis);
        gender_tv=findViewById(R.id.gender);
        time_tv=findViewById(R.id.time_dis);
        date_tv=findViewById(R.id.date_dis);
        count_tv=findViewById(R.id.country);
        set=in.getParcelableExtra("userData");


        name_tv.setText(""+set.getName());
        email_tv.setText(""+set.getEmail());
        num_tv.setText(""+set.getNum());
        gender_tv.setText(""+set.getGender());
        date_tv.setText(""+set.getDate());
        time_tv.setText(""+set.getTime());
        count_tv.setText(""+set.getCountry());


    }
}
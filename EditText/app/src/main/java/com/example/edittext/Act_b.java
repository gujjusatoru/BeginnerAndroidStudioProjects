package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Act_b extends AppCompatActivity {
    TextView id_tv, name_tv, pass_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_b);
        id_tv=findViewById(R.id.usrID);
        name_tv=findViewById(R.id.usrnm);
        pass_tv=findViewById(R.id.pass);
        Intent i=getIntent();
        String name= i.getStringExtra("name");
        String ID=i.getStringExtra("ID");
        String pass=i.getStringExtra("pass");
        name_tv.setText(""+name);
        id_tv.setText(""+ID);
        pass_tv.setText(""+pass);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume() was called", Toast.LENGTH_LONG).show();
    }
}
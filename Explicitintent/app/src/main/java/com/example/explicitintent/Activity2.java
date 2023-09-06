package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    TextView act2_tv;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        act2_tv=findViewById(R.id.act2);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n= new Intent(getApplicationContext(),Activity3.class);
                n.putExtra("Prev","Activity 2");
                startActivity(n);
            }
        });
        Intent in=getIntent();
        String t= in.getStringExtra("Prev");
        Toast.makeText(this, "You came from "+t, Toast.LENGTH_SHORT).show();
    }
}
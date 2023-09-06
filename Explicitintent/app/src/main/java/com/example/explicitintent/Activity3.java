package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    TextView act3_tv;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        act3_tv=findViewById(R.id.act3);
        btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m= new Intent(getApplicationContext(),MainActivity.class);
                m.putExtra("Prev","Activity 3");
                startActivity(m);
            }
        });
        Intent in=getIntent();
        String t= in.getStringExtra("Prev");
        Toast.makeText(this, "You came from "+t, Toast.LENGTH_SHORT).show();
    }
}
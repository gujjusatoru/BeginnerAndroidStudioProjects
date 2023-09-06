package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView act1_tv;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act1_tv=findViewById(R.id.act1);
        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Activity2.class);
                i.putExtra("Prev","Activity 1");
                startActivity(i);
            }

        });
        Intent f=getIntent();
        String h= f.getStringExtra("Prev");
        Toast.makeText(this, "You came from "+h, Toast.LENGTH_SHORT).show();
    }
}
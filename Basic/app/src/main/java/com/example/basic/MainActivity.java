package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    EditText edt;
    Button btt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.text1);
        edt=findViewById(R.id.edt);
        btt=findViewById(R.id.button001);
        String input= edt.getText().toString();
        img=findViewById(R.id.img1);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "The text is entered", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
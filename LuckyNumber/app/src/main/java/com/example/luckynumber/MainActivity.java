package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText name_tv;
    Button generate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_tv=findViewById(R.id.name);
        generate_btn=findViewById(R.id.btn1);
        generate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_tv.getText().toString().equals("")){
                    name_tv.setError("No Name entered");
                    name_tv.requestFocus();
                }
                else if(name_tv.getText().toString().matches(".*\\d.*")){
                    name_tv.setError("No number allowed");
                    name_tv.requestFocus();
                }
//                else if(name_tv.getText().toString().matches(".*\\s.*")){
//                    name_tv.setError("No whitespaces allowed");
//                    name_tv.requestFocus();
//                }
                else if(name_tv.getText().toString().matches(".*[\"'\\n].*")){
                    name_tv.setError("No other character allowed");
                    name_tv.requestFocus();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), Share.class);
                    i.putExtra("name", name_tv.getText().toString());
                    startActivity(i);
                }

            }
        });

    }

}
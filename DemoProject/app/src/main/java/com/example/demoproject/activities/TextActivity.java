package com.example.demoproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoproject.R;

public class TextActivity extends AppCompatActivity {
    Button back, done;
    EditText add_txt;
    String added_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        add_txt=findViewById(R.id.editText);
        back=findViewById(R.id.backbtn);
        done=findViewById(R.id.donebtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                added_txt=add_txt.getText().toString();
                if(added_txt.equals("")){
                    Toast.makeText(TextActivity.this, "No text added!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent();
                    i.putExtra("text",added_txt);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });

    }
}
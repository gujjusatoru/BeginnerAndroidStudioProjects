package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText usrnm_ed, usrID_ed, pswd_ed;
    Button btn_submit, btn_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrnm_ed=findViewById(R.id.name);
        usrID_ed=findViewById(R.id.ID);
        pswd_ed=findViewById(R.id.Pass);
        btn_submit=findViewById(R.id.Submit);
        btn_reset=findViewById(R.id.Reset);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=usrnm_ed.getText().toString();
                String pass=pswd_ed.getText().toString();
                String ID=usrID_ed.getText().toString();
                Intent i= new Intent(getApplicationContext(),Act_b.class);
                i.putExtra("name",name);
                i.putExtra("pass",pass);
                i.putExtra("ID",ID);
                startActivity(i);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrnm_ed.setText("");
                usrID_ed.setText("");
                pswd_ed.setText("");
            }
        });


    }
}
package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondAct extends AppCompatActivity {
    ImageView flag_img;
    TextView name_txt,lang_txt;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        flag_img=findViewById(R.id.image_flag);
        name_txt=findViewById(R.id.name);
        lang_txt=findViewById(R.id.language);
        Intent g=getIntent();
        name_txt.setText(g.getStringExtra("name"));
        lang_txt.setText(g.getStringExtra("lang"));
        int im=g.getIntExtra("flag",0);
        flag_img.setImageResource(im);
        btn_back=findViewById(R.id.backbtn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
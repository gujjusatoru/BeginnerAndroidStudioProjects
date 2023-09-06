package com.example.colorview.contained;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.colorview.MainActivity;
import com.example.colorview.R;

public class SecondActivity extends AppCompatActivity {
    TextView color_text;
    Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        color_text=findViewById(R.id.secondtext);
        back_btn=findViewById(R.id.buttonback);
        Intent in=getIntent();
        String text=in.getStringExtra("name");
        color_text.setText(""+text);
        int color=in.getIntExtra("color",0);
        color_text.setBackground(getDrawable(color));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                Intent i=new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
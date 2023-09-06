package com.example.travelanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;
    Button btn;
    ImageView car;
    ConstraintLayout layout;
    Animation animate_btn,animate_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        car=findViewById(R.id.car);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        layout=findViewById(R.id.layout);
        animate_text=AnimationUtils.loadAnimation(this,R.anim.text_animate);
        animate_btn= AnimationUtils.loadAnimation(this,R.anim.btn);
        btn.setAnimation(animate_btn);
        car.setAnimation(animate_btn);
        text1.setAnimation(animate_text);
        text2.setAnimation(animate_text);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                car.startAnimation(animate_btn);
                text1.startAnimation(animate_text);
                text2.startAnimation(animate_text);
                btn.startAnimation(animate_btn);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), ActivitySecond.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });

    }

    @Override
    protected void onResume() {
        car.startAnimation(animate_btn);
        text1.startAnimation(animate_text);
        text2.startAnimation(animate_text);
        btn.startAnimation(animate_btn);
        super.onResume();
    }
}
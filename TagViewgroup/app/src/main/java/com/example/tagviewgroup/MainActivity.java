package com.example.tagviewgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout main=new RelativeLayout(this);
        main.setBackgroundColor(Color.parseColor("#ff00aa"));

        setContentView(main);
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_END,RelativeLayout.TRUE);
            params.rightMargin=100;
        params.topMargin=100;
        params.leftMargin=100;
            TextView text= new TextView(this);
            text.setText("hello");
            text.setLayoutParams(params);
            main.addView(text);
            int id= View.generateViewId();
            text.setId(id);
           RelativeLayout.LayoutParams param2= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
           param2.addRule(RelativeLayout.ALIGN_PARENT_END);
           param2.addRule(RelativeLayout.BELOW,id);
           param2.rightMargin=300;

        TextView text2= new TextView(this);
        text2.setText("hello2");

        main.addView(text2,param2);

//        TagLayout tagLayout=new TagLayout(this);
//        for(int i=0; i<20;i++){
//            TextView text=new TextView(tagLayout.getContext());
//            text.setText("hello"+i);
//            tagLayout.addView(text);
//        }




    }
}
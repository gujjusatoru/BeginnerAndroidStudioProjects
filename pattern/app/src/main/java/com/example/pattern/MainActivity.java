package com.example.pattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(getApp().getAppName()+" : Downloads="+getApp().getAppDownloads()+", Ratings="+ getApp().getAppRatings());
            }
        });
    }

    public MyModel getApp(){
        return new MyModel(
                "Dahlia", 4800,3.8f
        );
    }
}
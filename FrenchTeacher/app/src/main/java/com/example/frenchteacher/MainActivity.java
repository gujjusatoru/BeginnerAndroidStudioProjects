package com.example.frenchteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Voice(View view){
        Button btn= (Button) view;
        MediaPlayer mediaPlayer=MediaPlayer.create(this,getResources().getIdentifier(btn.getTag().toString(),"raw",getPackageName()));
        mediaPlayer.start();

    }
}
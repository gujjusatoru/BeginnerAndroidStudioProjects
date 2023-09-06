package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Share extends AppCompatActivity {
    TextView number_tv;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        number_tv=findViewById(R.id.result);
        share_btn=findViewById(R.id.btn2);
        Intent gt= getIntent();
        String name= gt.getStringExtra("name");
        Toast.makeText(this, "Congratulations, "+name+"!!", Toast.LENGTH_SHORT).show();
        int number=numberGenerate();
        number_tv.setText(""+number);
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(name,number);

            }
        });



    }
    public int numberGenerate(){
        Random random= new Random();
        return random.nextInt(1000);
    }
    public void shareData(String user, int random){
        String num= String.valueOf(random);
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,user+" just got lucky!!");
        i.putExtra(Intent.EXTRA_TEXT,num+" is their lucky number.");
        startActivity(Intent.createChooser(i,"Choose a platform"));
    }
}
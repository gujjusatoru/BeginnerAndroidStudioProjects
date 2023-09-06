package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edt_txt;
    TextView txt_vw;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_txt=findViewById(R.id.editText);
        txt_vw=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        DisplayPrevious();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=edt_txt.getText().toString();

                displayAndSaveText(text);
            }
        });

    }

    private void DisplayPrevious() {
        SharedPreferences sharedPreferences=getSharedPreferences("shared",MODE_PRIVATE);
        String s= sharedPreferences.getString("data"," ");
        txt_vw.setText(""+s);
    }

    private void displayAndSaveText(String data) {
        txt_vw.setText(""+data);
        SharedPreferences sharedPreferences=getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("data",data);
        editor.commit();
    }
}
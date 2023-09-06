package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result_tv;
    EditText input_ed;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result);
        input_ed=findViewById(R.id.input);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double val= Double.parseDouble(input_ed.getText().toString());
                double pound= convert(val);
                result_tv.setText(""+pound+"pounds");
            }
            public double convert(double kilo){
                double res=kilo*2.205;
                return res;
            }
        });

    }
}
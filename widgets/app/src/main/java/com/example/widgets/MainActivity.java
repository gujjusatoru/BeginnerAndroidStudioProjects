package com.example.widgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.widgets.included_classes.datepicker;
import com.example.widgets.included_classes.timepicker;

public class MainActivity extends AppCompatActivity {
    Spinner gender_spn;
    Button time_btn, date_btn;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=findViewById(R.id.progressBar);
        bar.setProgress(50);
        gender_spn=findViewById(R.id.gender);
        String[] genders={"Male","Female","Trans","Non-Binary","Fluid","Won't say"};
        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,genders);
        ad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gender_spn.setAdapter(ad);
        gender_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "You selected: "+genders[i], Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        time_btn=findViewById(R.id.time);
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeP=new timepicker();
                timeP.show(getSupportFragmentManager(),"Pick Time");
                bar.incrementProgressBy(25);
            }
        });
        date_btn=findViewById(R.id.dob);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dob=new datepicker();
                dob.show(getSupportFragmentManager(),"Pick Date");
                bar.incrementProgressBy(25);
            }
        });


    }
}
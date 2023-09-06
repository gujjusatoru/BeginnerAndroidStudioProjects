package com.example.datamodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datamodel.contained.DataActivity;
import com.example.datamodel.contained.Datepick;
import com.example.datamodel.contained.Timepick;
import com.example.datamodel.contained.UserData;

public class MainActivity extends AppCompatActivity implements Timepick.GoBetween, Datepick.GoBetweendate{
    UserData data;
    boolean check=false;
    String time,gender,date,country,name,email, phoneNumber;
    EditText name_ed, email_ed, num_ed;
    Spinner country_spn;
    Button time_btn, date_btn, reset_btn,submit_btn;
    RadioButton rad;

    @Override
    public void getDate(String x) {
        date=x;
        date_btn.setText(date);
    }

    @Override
    public void getData(String x) {
        time=x;
        time_btn.setText(time);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_ed=findViewById(R.id.name);
        email_ed=findViewById(R.id.email);
        num_ed=findViewById(R.id.number);
        RadioGroup rdgrp=findViewById(R.id.radioGroup);
        reset_btn=findViewById(R.id.reset);
        submit_btn=findViewById(R.id.submit);
        Intent i= new Intent(getApplicationContext(), DataActivity.class);
        rdgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rad=findViewById(i);
                gender=rad.getText().toString();
                check=true;

            }
        });
        country_spn=findViewById(R.id.spinner);
        String[] count={"UAE","US","UK","China","Bangladesh","India","Australia"};
        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,count);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_spn.setAdapter(ad);
        country_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country=count[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                country=null;

            }
        });
        time_btn=findViewById(R.id.time);
        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeP = new Timepick();
                timeP.show(getSupportFragmentManager(), "Pick Time");
            }
        });
        date_btn=findViewById(R.id.date);
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateP=new Datepick();
                dateP.show(getSupportFragmentManager(),"Pick Date");
//                date_btn.setText(date);
            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_ed.setText("");
                email_ed.setText("");
                num_ed.setText("");
                gender=null;
                time=null;
                time_btn.setText("PICK TIME");
                date_btn.setText("PICK DOB");
                if(check){
                int id= rdgrp.getCheckedRadioButtonId();
                rad=findViewById(id);
                rad.setChecked(false);}
                country_spn.setSelection(0);
                country=null;
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=name_ed.getText().toString();
                email=email_ed.getText().toString();
                phoneNumber =num_ed.getText().toString();
                boolean isAllExist = checkValues();
                if(isAllExist){
                data = new UserData();
                data.setName(name);
                data.setEmail(email);
                data.setNum(phoneNumber);
                data.setGender(gender);
                data.setDate(date);
                data.setTime(time);
                data.setCountry(country);
                i.putExtra("userData", data);
                startActivity(i);}
                else Toast.makeText(MainActivity.this, "Complete all fields!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkValues() {
        if(name.equals("") ||
                email.equals("") ||
                phoneNumber.equals("") ||
                gender==null ||
                date==null ||
                time==null ||
                country==null)
            return false;
        else return true;

    }


}
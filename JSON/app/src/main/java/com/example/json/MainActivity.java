package com.example.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
DataBaseHelperClass dataHelper;
ArrayList<TemplateData> templateDataArrayList;
ConstraintLayout data;
ExtractData extract;
SQLiteDatabase db;
ListView list;
TextView textJSON;
Button click;
String[] id;
EditText enterData;
JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        templateDataArrayList=new ArrayList<>();
        obj=new JSONObject();
        data=findViewById(R.id.data);
        textJSON=findViewById(R.id.textJSON);
        enterData=findViewById(R.id.edit);
        click=findViewById(R.id.btn);
        dataHelper=new DataBaseHelperClass(this);
        try {
            dataHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=dataHelper.openDataBase();
                extract=new ExtractData(db,MainActivity.this);
                id=enterData.getText().toString().split(",");
                templateDataArrayList.addAll(extract.getAllData(id));
                if(templateDataArrayList!=null){
                    obj=extract.convertData(templateDataArrayList);
                    data.setVisibility(View.VISIBLE);
                    Log.i("Json data", "Json Obj: " +obj.toString());
                    textJSON.setText(obj.toString());
                }
                dataHelper.close();
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(textJSON.getText().toString().equals(""))
            super.onBackPressed();
        else{
            id=null;
            enterData.setText("");
            data.setVisibility(View.GONE);
        }
    }
}
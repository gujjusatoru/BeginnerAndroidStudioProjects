package com.example.json;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    DataBaseHelperClass dataHelper;
ArrayList<TemplateData> templateDataArrayList;
ConstraintLayout data;
ExtractData extract;
SQLiteDatabase db;
ListView list;
TextView textJSON;
String[] id;
Button click;
EditText enterData;
JSONObject obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
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
                String[] get=enterData.getText().toString().split(",");
                Log.i("split", "onClick: "+get.length);
                for(int i=0;i<get.length;i++){
                 String[] id;
                 id=get[i].split(" ");
                 templateDataArrayList.addAll(extract.getAllData(id));
                 try {
                     obj.put("Data" + get[i], extract.convertData(templateDataArrayList, id));
                 } catch (JSONException e) {
                     throw new RuntimeException(e);
                 }
                }
//                id=enterData.getText().toString().split(",");
                if(obj!=null) {
                    data.setVisibility(View.VISIBLE);
                    Log.i("Json data", "Json Obj: " + obj.toString());
                    textJSON.setText(obj.toString());
                    try {
                        File stringFile= new File("/storage/emulated/0/Documents");
                        if (!stringFile.exists()&&!stringFile.mkdir()) {
                            stringFile.mkdir();
                        }
                        File childFolder = new File(stringFile, "data");
                        if (!childFolder.exists()&&!childFolder.mkdir()) {
                            childFolder.mkdirs();
                        }
                        File text=new File(childFolder,"text.txt");
                        if (!text.exists()) {
                            boolean fileCreated = text.createNewFile();
                            if (!fileCreated) {
                            }
                        }
                        FileOutputStream fos=new FileOutputStream(text);
                        OutputStreamWriter out= new OutputStreamWriter(fos);
                        out.write(textJSON.getText().toString());
                        out.close();

                        Toast.makeText(MainActivity.this, "The contents are saved in the file.", Toast.LENGTH_SHORT).show();
                    }

                    catch (Throwable t) {

                        Toast.makeText(MainActivity.this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();

                    }
                }
                dataHelper.close();
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, permissions[i] + "permission granted", Toast.LENGTH_LONG).show();
//                if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED|| checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                } else {
                    requestPermissions(permissions, requestCode);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(textJSON.getText().toString().equals(""))
            super.onBackPressed();
        else{
            textJSON.setText("");
            enterData.setText("");
            id=null;
            data.setVisibility(View.GONE);
        }
    }

}
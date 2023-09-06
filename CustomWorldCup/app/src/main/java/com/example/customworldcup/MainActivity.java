package com.example.customworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customworldcup.contained.CountryModelClass;
import com.example.customworldcup.contained.CutomAdaptor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_vw;
    private  CutomAdaptor adapter;
    ArrayList<CountryModelClass> modelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_vw=findViewById(R.id.listView);
        modelData=new ArrayList<>();
        modelData.add(new CountryModelClass("Australia","5",R.drawable.au_flag));
        modelData.add(new CountryModelClass("India","2",R.drawable.in_flag));
        modelData.add(new CountryModelClass("West Indies","2",R.drawable.wi_flag));
        modelData.add(new CountryModelClass("Pakistan","1",R.drawable.pk_flag));
        modelData.add(new CountryModelClass("Sri Lanka","1",R.drawable.sl_flag));
        modelData.add(new CountryModelClass("New Zealand","0",R.drawable.nz_flag));
        modelData.add(new CountryModelClass("South Africa","0",R.drawable.sa_flag));

        adapter=new CutomAdaptor(modelData,getApplicationContext());
        list_vw.setAdapter(adapter);

        list_vw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Country: "+adapter.getItem(i).getName()+"\nCups won: "+adapter.getItem(i).getCupWon(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
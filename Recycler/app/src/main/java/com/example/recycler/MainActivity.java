package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    RecyclerView recyclerView;
    LangModel[] langModels;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle);

        langModels=new LangModel[]{
                new LangModel("Brazil","Portuguese",R.drawable.br_flag),
                new LangModel("France","French",R.drawable.fr_flag),
                new LangModel("South Africa","Around 35 languages, but the major language is:\n Zulu",R.drawable.sa_flag),
                new LangModel("Colombia","Colombian Spanish",R.drawable.co_flag),
                new LangModel("Pakistan","Urdu",R.drawable.pk_flag),
                new LangModel("Greece","Greek",R.drawable.gr_flag),
                new LangModel("Singapore","Malay",R.drawable.sg_flag),
                new LangModel("India","Around 22 languages, but the official national language is:\n Hindi",R.drawable.in_flag),
                new LangModel("Taiwan","Mandarin",R.drawable.tw_flag),
                new LangModel("Sri Lanka","Sinhalese",R.drawable.sl_flag)
        };


        adapter=new MyAdapter(langModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setClickedItem(this);

    }

    @Override
    public void OnClick(View view, int pos) {
        Intent i=new Intent(getApplicationContext(), SecondAct.class);
        i.putExtra("name",langModels[pos].getCountry_name());
        i.putExtra("lang",langModels[pos].getLanguage());
        i.putExtra("flag",langModels[pos].getFlag());
        startActivity(i);
    }
}
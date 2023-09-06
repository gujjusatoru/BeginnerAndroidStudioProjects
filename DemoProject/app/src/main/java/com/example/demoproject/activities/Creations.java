package com.example.demoproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.adapter.ClickedItem;
import com.example.demoproject.adapter.CreationsAdapter;
import com.example.demoproject.data.CreationsModel;

import java.io.File;
import java.util.ArrayList;

public class Creations extends AppCompatActivity implements ClickedItem {
    Button back, delete, open, cancel;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CreationsAdapter adapter;
    ArrayList<CreationsModel> creationsModels;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creations);
        recyclerView=findViewById(R.id.recyclerList);
        back=findViewById(R.id.backbtn);
        dialog=new Dialog(Creations.this);
        creationsModels=new ArrayList<>();
        Intent i=getIntent();
        String path=i.getStringExtra("path");
        File directory=new File(String.valueOf(Uri.parse(path)));
        File[] files=directory.listFiles();
        if(files!=null) {
            for (int num = 0; num < files.length; num++) {
                creationsModels.add(new CreationsModel(files[num].getName(), files[num].getPath()));
            }
        }
        else{
            Toast.makeText(this, "No creations yet!", Toast.LENGTH_SHORT).show();
        }
        instantiateCreations();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void instantiateCreations() {
        layoutManager=new GridLayoutManager(getApplicationContext(), 2);
        adapter=new CreationsAdapter(getApplicationContext(),creationsModels);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickedItem(this);
    }

    @Override
    public void onSelected(int type, View view, int pos) {
        CreationsModel create=creationsModels.get(pos);
        Intent share= new Intent(getApplicationContext(), ShareImage.class);
        share.putExtra("path",create.getImage());
        startActivity(share);
    }

    @Override
    public void longSelected(View view, int pos) {

        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        open = dialog.findViewById(R.id.open);
        delete = dialog.findViewById(R.id.delete);
        cancel = dialog.findViewById(R.id.cancel);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CreationsModel create=creationsModels.get(pos);
                Intent share= new Intent(getApplicationContext(), ShareImage.class);
                share.putExtra("path",create.getImage());
                startActivity(share);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                File file = new File(creationsModels.get(pos).getImage());
                file.delete();
                getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(creationsModels.get(pos).getImage()))));
                creationsModels.remove(pos);
                adapter.notifyItemRemoved(pos);

                }
            });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Creations.this, "Dialog closed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

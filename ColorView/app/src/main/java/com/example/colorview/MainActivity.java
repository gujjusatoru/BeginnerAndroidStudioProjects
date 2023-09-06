package com.example.colorview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colorview.contained.AdapterCustom;
import com.example.colorview.contained.ColorData;
import com.example.colorview.contained.SecondActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_view;
    Button okay_btn,delete_btn,cancel_btn;
    private AdapterCustom adapter;
    ArrayList<ColorData> colorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view=findViewById(R.id.listView);
        Dialog dialog= new Dialog(MainActivity.this);

        colorModel=new ArrayList<>();
        colorModel.add(new ColorData("Red",R.color.red));
        colorModel.add(new ColorData("Yellow",R.color.yellow));
        colorModel.add(new ColorData("Pink",R.color.pink));
        colorModel.add(new ColorData("Orange",R.color.orange));
        colorModel.add(new ColorData("Black",R.color.black));
        colorModel.add(new ColorData("Green",R.color.green));
        colorModel.add(new ColorData("Purple",R.color.purple));
        colorModel.add(new ColorData("Brown",R.color.brown));
        colorModel.add(new ColorData("Blue",R.color.blue));

        adapter=new AdapterCustom(colorModel,getApplicationContext());
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.color.blur));
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                cancel_btn=dialog.findViewById(R.id.cancel);
                okay_btn=dialog.findViewById(R.id.open);
                delete_btn=dialog.findViewById(R.id.delete);
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Dialog Closed!", Toast.LENGTH_SHORT).show();
                    }
                });
                okay_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent i=new Intent(getApplicationContext(), SecondActivity.class);
                        i.putExtra("name",adapter.getItem(position).getColor_name());
                        i.putExtra("color",adapter.getItem(position).getColor());
                        startActivity(i);

                    }
                });
                delete_btn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          dialog.dismiss();
                          Toast.makeText(MainActivity.this, adapter.getItem(position).getColor_name()+" removed!!", Toast.LENGTH_SHORT).show();
                          colorModel.remove(position);
                          adapter.notifyDataSetChanged();

                      }
                });


                dialog.show();
            }
        });

//        Intent pre=getIntent();

    }
}
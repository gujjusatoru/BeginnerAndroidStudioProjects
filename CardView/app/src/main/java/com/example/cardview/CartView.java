package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartView extends AppCompatActivity implements OnClick{
    RecyclerView recyclerView2;
    ArrayList<CardDataModel> itemlist;
    CardAdapter myadapter;
    Button backbtn, emptybtn, delete_btn,cancel_btn,add_btn;
    TextView header;
    Dialog dialog;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        Intent get=getIntent();
        recyclerView2=findViewById(R.id.recycle2);
        backbtn=findViewById(R.id.backbtn);
        emptybtn=findViewById(R.id.empty2);
        dialog= new Dialog(this);

        itemlist=new ArrayList<>(get.getParcelableArrayListExtra("selected"));
        count=itemlist.size();
        header=findViewById(R.id.head2);
        header.setText("Your Cart ("+count+")");
        myadapter=new CardAdapter(this,itemlist);
        RecyclerView.LayoutManager lay= new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(lay);
        recyclerView2.setAdapter(myadapter);
        myadapter.setClickedItem(this);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putParcelableArrayListExtra("setdata",itemlist);
                setResult(RESULT_OK,i);
                    onBackPressed();
            }
        });
        emptybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist.clear();
                count=0;
                header.setText("Your Cart");
                Toast.makeText(CartView.this, "Cart Emptied!!", Toast.LENGTH_SHORT).show();
                myadapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view, int pos) {
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.back));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        add_btn=dialog.findViewById(R.id.add);
        delete_btn=dialog.findViewById(R.id.delete);
        cancel_btn=dialog.findViewById(R.id.cancel);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                count++;
                header.setText("Your Cart ("+count+")");
                Toast.makeText(CartView.this, itemlist.get(pos).getCardName()+" added!", Toast.LENGTH_SHORT).show();
                itemlist.add(itemlist.get(pos));
                myadapter.notifyDataSetChanged();
            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                count--;
                header.setText("Your Cart ("+count+")");
                Toast.makeText(CartView.this, itemlist.get(pos).getCardName()+" deleted!", Toast.LENGTH_SHORT).show();
                itemlist.remove(pos);
                myadapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }
}
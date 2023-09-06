package com.example.foodcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements OnClick {
    ArrayList<CardDataModel> cardDataModels;
    List<CardDataModel> list;
    RecyclerView recycle_vw;
    TextView header, counter;
    AdapterClass adapter;
    Dialog dialog;

    int count=0,itemcount=1;
    Button menu_btn, cart_btn,add_btn,cancel_btn,delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle_vw=findViewById(R.id.recycle2);
        header=findViewById(R.id.head2);
        menu_btn=findViewById(R.id.menu);
        cart_btn=findViewById(R.id.cart);
        dialog=new Dialog(MainActivity.this);

        cardDataModels = new ArrayList<>();
        cardDataModels.add(new CardDataModel("Samosa", R.drawable.samosa, false,0));
        cardDataModels.add(new CardDataModel("Burger", R.drawable.burger, false,0));
        cardDataModels.add(new CardDataModel("Chole Bhature", R.drawable.chole_bhature, false,0));
        cardDataModels.add(new CardDataModel("Garlic Bread", R.drawable.garlic_bread, false,0));
        cardDataModels.add(new CardDataModel("Gol Gappe", R.drawable.golgappe, false,0));
        cardDataModels.add(new CardDataModel("Pasta", R.drawable.pasta, false,0));
        cardDataModels.add(new CardDataModel("Momos", R.drawable.momo, false,0));
        cardDataModels.add(new CardDataModel("Sandwich", R.drawable.sandwich1, false,0));
        cardDataModels.add(new CardDataModel("Aloo Kachori", R.drawable.kachori, false,0));
        cardDataModels.add(new CardDataModel("Pizza", R.drawable.pizza, false,0));
        cardDataModels.add(new CardDataModel("Chur Chur Thali", R.drawable.churchur_thali, false,0));

        adapter = new AdapterClass(this, cardDataModels,false);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager
                        (this, LinearLayoutManager.VERTICAL, false);
        recycle_vw.setLayoutManager(layout);
        recycle_vw.setAdapter(adapter);
        adapter.setClickedItem(this);

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    Toast.makeText(MainActivity.this, "Cart is Empty!! Add something.", Toast.LENGTH_SHORT).show();
                }
                else{
                menu_btn.setText("Back to menu");
                header.setText("Your Cart("+count+")");
                cart_btn.setText("Confirm");

                Stream<CardDataModel> cardDataModelStream = cardDataModels.stream().filter(x -> x.isSelect());
                list= cardDataModelStream.collect(Collectors.toList());
//                adapter=new AdapterClass(getApplicationContext(), (ArrayList<CardDataModel>) cardDataModels.stream()
//                        .filter(x -> x.isSelect()),true);
                adapter=new AdapterClass(getApplicationContext(), (ArrayList<CardDataModel>) list,true);
                recycle_vw.setAdapter(adapter);
                adapter.setClickedItem(MainActivity.this);}
            }
        });
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu_btn.getText().equals("Empty Cart")){
                    if(count==0){
                        Toast.makeText(MainActivity.this, "Cart is Empty!! Add something.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    for(int i=0;i<cardDataModels.size();i++){
                        cardDataModels.get(i).setSelect(false);
                    }
                    count=0;
                    cart_btn.setText("Your Cart");
                    adapter.notifyDataSetChanged();
                    recycle_vw.setAdapter(adapter);
                    adapter.setClickedItem(MainActivity.this);}
                }
                else{
                    adapter=new AdapterClass(getApplicationContext(),cardDataModels,false);
                    recycle_vw.setAdapter(adapter);
                    menu_btn.setText("Empty Cart");
                    header.setText("Choose Your Meal!");
                    cart_btn.setText("Your Cart("+count+")");
                    adapter.setClickedItem(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onClick(View view, int pos) {
        if(header.getText().equals("Choose Your Meal!")) {
            if (cardDataModels.get(pos).isSelect()) {
                cardDataModels.get(pos).setSelect(false);
                count--;
                if(count>0)
                cart_btn.setText("Your Cart("+count+")");
                else cart_btn.setText("Your Cart");
                view.setBackgroundResource(R.drawable.white);
                Toast.makeText(this, cardDataModels.get(pos).getCardName() + " removed!", Toast.LENGTH_SHORT).show();

            } else {
                cardDataModels.get(pos).setSelect(true);
                count++;
                cart_btn.setText("Your Cart("+count+")");
                view.setBackgroundResource(R.drawable.cardbg);
                Toast.makeText(this, cardDataModels.get(pos).getCardName() + " added!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            counter=view.findViewById(R.id.count);
            dialog.setContentView(R.layout.dialog_layout);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.back));
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.show();
            add_btn = dialog.findViewById(R.id.add);
            delete_btn = dialog.findViewById(R.id.delete);
            cancel_btn = dialog.findViewById(R.id.cancel);
            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        dialog.dismiss();
                        int i= Integer.parseInt(counter.getText().toString());
                        i++;
                        counter.setText(""+i);
                        cardDataModels.get(pos).setCardCount(i);
                        count++;
                        header.setText("Your Cart(" + count + ")");
                        view.setBackgroundResource(R.drawable.cardbg);
                        Toast.makeText(MainActivity.this, list.get(pos).getCardName() + " added!", Toast.LENGTH_SHORT).show();

                }
            });
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    int i = Integer.parseInt(counter.getText().toString());
                    i--;
                    if (i > 0) {
                        counter.setText("" + i);
                        count--;
                        header.setText("Your Cart(" + count + ")");
                        view.setBackgroundResource(R.drawable.cardbg);
                        Toast.makeText(MainActivity.this, list.get(pos).getCardName() + " removed!", Toast.LENGTH_SHORT).show();
                    } else {
                        count--;
                        header.setText("Your Cart(" + count + ")");
                        Toast.makeText(MainActivity.this, list.get(pos).getCardName() + " removed!", Toast.LENGTH_SHORT).show();
                        cardDataModels.get(pos).setSelect(false);
                        list.remove(pos);
                        adapter.notifyDataSetChanged();
                        recycle_vw.setAdapter(adapter);
                    }
                }
            });
            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Dialog closed!", Toast.LENGTH_SHORT).show();
                }
            });

        }



    }
}
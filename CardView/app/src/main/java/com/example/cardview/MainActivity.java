package com.example.cardview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClick {
    //1.Data
    ArrayList<CardDataModel> cardDataModels, selectedData;
    int rqstcode = 1;
    int count = 0;

    //2.Adapter View
    RecyclerView recycle_vw;
    //3.Adapter
    CardAdapter adapter;
    Button cart_btn, empty_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart_btn = findViewById(R.id.cart);
        empty_btn = findViewById(R.id.empty);
        selectedData = new ArrayList<>();
        recycle_vw = findViewById(R.id.recycle2);
        cardDataModels = new ArrayList<>();
        cardDataModels.add(new CardDataModel("Samosa", R.drawable.samosa, false));
        cardDataModels.add(new CardDataModel("Burger", R.drawable.burger, false));
        cardDataModels.add(new CardDataModel("Chole Bhature", R.drawable.chole_bhature, false));
        cardDataModels.add(new CardDataModel("Garlic Bread", R.drawable.garlic_bread, false));
        cardDataModels.add(new CardDataModel("Gol Gappe", R.drawable.golgappe, false));
        cardDataModels.add(new CardDataModel("Pasta", R.drawable.pasta, false));
        cardDataModels.add(new CardDataModel("Momos", R.drawable.momo, false));
        cardDataModels.add(new CardDataModel("Sandwich", R.drawable.sandwich1, false));
        cardDataModels.add(new CardDataModel("Aloo Kachori", R.drawable.kachori, false));
        cardDataModels.add(new CardDataModel("Pizza", R.drawable.pizza, false));
        cardDataModels.add(new CardDataModel("Chur Chur Thali", R.drawable.churchur_thali, false));

        adapter = new CardAdapter(this, cardDataModels);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager
                        (this, LinearLayoutManager.VERTICAL, false);
        recycle_vw.setLayoutManager(layout);
        recycle_vw.setAdapter(adapter);
        adapter.setClickedItem(this);
        empty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0) {
                    count = 0;
                    cart_btn.setText("View Cart");
                    Toast.makeText(MainActivity.this, "Cart Emptied!!", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<cardDataModels.size();i++){
                        cardDataModels.get(i).setSelect(false);
                    }
                    recycle_vw.setAdapter(adapter);
                    selectedData.clear();
                } else
                    Toast.makeText(MainActivity.this, "Nothing is in cart!! Add something.", Toast.LENGTH_SHORT).show();
            }
        });
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedData.size() == 0) {
                    Toast.makeText(MainActivity.this, "Nothing is in cart!! Add something.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), CartView.class);
                    i.putParcelableArrayListExtra("selected", selectedData);
                    startActivityForResult(i, rqstcode);
//                startActivity(i);
                }
            }
        });
    }

    @Override
    public void onClick(View view, int pos) {
//        for(int n=0;n<arr.length;n++){
//            if(arr[n]==pos){
//                count--;
//                view.setBackground(getDrawable(R.drawable.white));
//                arr[n]=0;
//                if (count == 0) {
//                    cart_btn.setText("View Cart");
//                } else {
//                    cart_btn.setText("View Cart (" + count + ")");
//                }
//
//            }
//            else {
//                arr[n]=pos;
        boolean check = true;
        if (selectedData.size() == 0) {
            count++;
            cart_btn.setText("View Cart (" + count + ")");
            view.setBackground(getDrawable(R.drawable.cardbg));
            cardDataModels.get(pos).setSelect(true);
            Toast.makeText(this, cardDataModels.get(pos).getCardName() + " added!!", Toast.LENGTH_SHORT).show();
            selectedData.add(new CardDataModel
                    (cardDataModels.get(pos).getCardName(), cardDataModels.get(pos).getCardImg(), cardDataModels.get(pos).isSelect()));
        } else {
            for (int n = 0; n < selectedData.size(); n++) {

                if (selectedData.get(n).getCardName().equals(cardDataModels.get(pos).getCardName())) {
                    view.setBackground(getDrawable(R.drawable.white));
                    count--;
                    if (count == 0) {
                        cart_btn.setText("View Cart");
                    } else {
                        cart_btn.setText("View Cart (" + count + ")");
                    }
                    selectedData.remove(n);
                    check = true;
                    break;
                } else check = false;
            }

            if (!check) {
                count++;
                cart_btn.setText("View Cart (" + count + ")");
                view.setBackground(getDrawable(R.drawable.cardbg));
                cardDataModels.get(pos).setSelect(true);
                Toast.makeText(this, cardDataModels.get(pos).getCardName() + " added!!", Toast.LENGTH_SHORT).show();
                selectedData.add(new CardDataModel
                        (cardDataModels.get(pos).getCardName(), cardDataModels.get(pos).getCardImg(), cardDataModels.get(pos).isSelect()));

            }


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == rqstcode) {
                selectedData = data.getParcelableArrayListExtra("setdata");
                if (selectedData.size() == 0) {
                    for(int i=0;i<cardDataModels.size();i++){
                        cardDataModels.get(i).setSelect(false);
                    }
                    recycle_vw.setAdapter(adapter);
                    count = 0;
                    cart_btn.setText("view cart");
                } else {
                    for (int k = 0; k < cardDataModels.size(); k++) {
                        for (int n = 0; n < selectedData.size(); n++) {
                            if (cardDataModels.get(k).getCardName().equals(selectedData.get(n).getCardName())) {
                                cardDataModels.get(k).setSelect(true);
                                break;
                            } else cardDataModels.get(k).setSelect(false);
                        }

                        adapter.notifyDataSetChanged();
                        cart_btn.setText("view cart ("+selectedData.size()+")");

                    }
                }
            }

        }
        //
    }
}
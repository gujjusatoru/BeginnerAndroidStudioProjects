package com.example.advancedrecyclerviews.recyclermanyview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class ManyViewRecycler extends AppCompatActivity {
    Button back;
    RecyclerView recyclerView;
    ArrayList<TravelModel> travelModelArrayList=new ArrayList<>();
    ManyViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_view_recycler);

        back=findViewById(R.id.manyback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView=findViewById(R.id.recycle4);

        createDataList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ManyViewAdapter(this,travelModelArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void createDataList() {
        travelModelArrayList.add(new TravelModel("Dudhsagar","Sonaulim, Goa","Waterfall"));
        travelModelArrayList.add(new TravelModel("COMO Pizzeria, 32nd Avenue","Pizza"));
        travelModelArrayList.add(new TravelModel("Hawa Mahal","Jaipur","Historic Palace"));
        travelModelArrayList.add(new TravelModel("Civil Lines Wale, Civil Line Sec 15","Chole Bhature"));
        travelModelArrayList.add(new TravelModel("Delhi Light, Railway Road","Chur-Chur Thali"));
        travelModelArrayList.add(new TravelModel("Tehri Dam","Tehri, Uttrakhand","Tallest Dam"));
        travelModelArrayList.add(new TravelModel("Delhi Chat Bhandar, ShivMurti","Golgappe"));
        travelModelArrayList.add(new TravelModel("Spezia Bistro, Ambience Mall","Garlic Bread"));
        travelModelArrayList.add(new TravelModel("Taj Mahal","Agra","Historic Tomb"));
        travelModelArrayList.add(new TravelModel("Monu Kachori Wala, Sadar Bazaar","Kachori"));
        travelModelArrayList.add(new TravelModel("Dal Lake","Srinagar","Lake"));
        travelModelArrayList.add(new TravelModel("Vyanjan Point, Chakkarpur Sec 28","Momos"));
        travelModelArrayList.add(new TravelModel("Good Flippin' Burgers, Sushant Shopping Arcade","Burger"));
        travelModelArrayList.add(new TravelModel("Chapora Fort","Goa","Fort"));
        travelModelArrayList.add(new TravelModel("Radhey Sweets, Laxman Vihar","Samosa"));
        travelModelArrayList.add(new TravelModel("Sabarmati Riverfront","Ahmedabad, Gujarat","RiverFront"));
        travelModelArrayList.add(new TravelModel("Xero Degrees, Sec 14","Pasta"));
        travelModelArrayList.add(new TravelModel("Sangam Valley","Leh","Confluence of two rivers"));
        travelModelArrayList.add(new TravelModel("Harish Bakery, New Colony","Everything"));
        travelModelArrayList.add(new TravelModel("Calangute Beach","Goa","Beach"));

    }
}
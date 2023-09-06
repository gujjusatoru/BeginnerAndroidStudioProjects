package com.example.advancedrecyclerviews.recyclerswipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.advancedrecyclerviews.MainActivity;
import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class SwipeRecycler extends AppCompatActivity {
    ArrayList<Spotify> spotifyArrayList=new ArrayList<>();
    RecyclerView recyclerView;
    Button back, show;
    SwipeAdapter adapter;
    TextView heading;
    int liked=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler);

        heading=findViewById(R.id.headswipe);
        back=findViewById(R.id.swipeback);
        show=findViewById(R.id.swipeshow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heading.getText().equals("Swipe View Recycler")){
                    onBackPressed();
                }
                else{
                    heading.setText("Swipe View Recycler");
                    adapter=new SwipeAdapter(SwipeRecycler.this,spotifyArrayList);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.likedSongs().size()>0){
                    heading.setText("Liked Songs");
                    adapter=new SwipeAdapter(SwipeRecycler.this,adapter.likedSongs());
                    recyclerView.setAdapter(adapter);

                }
                else {
                    Toast.makeText(SwipeRecycler.this, "Nothing Liked!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView=findViewById(R.id.recycle6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createList();
        adapter=new SwipeAdapter(this,spotifyArrayList);
        recyclerView.setAdapter(adapter);

    }

    void createList(){
        spotifyArrayList.add(new Spotify("Strawberries & Cigarettes","Troye Sivan",R.drawable.straw));
        spotifyArrayList.add(new Spotify("Stoned on You","Jaymes Young",R.drawable.stone));
        spotifyArrayList.add(new Spotify("Vienna","Billy Joel",R.drawable.vienna));
        spotifyArrayList.add(new Spotify("Paper Love","Allie X",R.drawable.paper));
        spotifyArrayList.add(new Spotify("I Found","Amber Run",R.drawable.found));
        spotifyArrayList.add(new Spotify("Lights Down Low","MAX",R.drawable.lightlows));
        spotifyArrayList.add(new Spotify("Dandelions","Ruth B.",R.drawable.dandelions));
        spotifyArrayList.add(new Spotify("Fly Me To The Moon","Frank Sinatra",R.drawable.moon));
        spotifyArrayList.add(new Spotify("Eastside","benny blanco, Halsey, Khalid",R.drawable.eastside));
        spotifyArrayList.add(new Spotify("Running Up That Hill","Kate Bush",R.drawable.runningup));
        spotifyArrayList.add(new Spotify("Slip","Elliot Moss",R.drawable.slip));

    }
}
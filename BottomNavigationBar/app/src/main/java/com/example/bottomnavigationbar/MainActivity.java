package com.example.bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    ProfileFragment profileFragment=new ProfileFragment();
    FeedFragment feedFragment=new FeedFragment();
    LikesFragment likesFragment=new LikesFragment();
    final int profile=R.id.profile,likes=R.id.likes,feed=R.id.feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.feed);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, profileFragment).commit();
            return true;
        }
        else if(id==feed){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, feedFragment).commit();
            return true;
        }
        else if(id==likes){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, likesFragment).commit();
            return true;
        }
        else return false;

    }
}
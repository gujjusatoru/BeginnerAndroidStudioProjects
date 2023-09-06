package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar tool;
    final int one=R.id.one,two=R.id.two,three=R.id.three;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tool=findViewById(R.id.tool);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav);
        setUpDrawerContent(navigationView);
    }

    private void setUpDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectMenuItem(item);
                return true;
            }
        });
    }

    private void selectMenuItem(MenuItem item) {
        Fragment fragment=null;
        Class fragmentClass;
        int id=item.getItemId();
        if(id==one){
                fragmentClass= FirstFragment.class;
                }
        else if (id==two) {
            fragmentClass= SecondFragment.class;
        }
        else if (id==three) {
            fragmentClass= ThirdFragment.class;
        }
        else fragmentClass= FirstFragment.class;

        try {
            fragment=(Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawer.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: drawer.openDrawer(GravityCompat.START);
                                    return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
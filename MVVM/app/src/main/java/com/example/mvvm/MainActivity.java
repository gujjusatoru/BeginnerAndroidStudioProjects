package com.example.mvvm;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding bind=ActivityMainBinding.inflate(getLayoutInflater());
        View view=bind.getRoot();
        setContentView(view);

//        bind.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModel.getappName();
//
//
//            }
//        });
//        viewModel= ViewModelProviders.of(this).get(ViewModel.class);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
//        viewModel.mutableLiveData.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                bind.textView.setText("" + s);
//            }
//        });
        bind.setViewModel(viewModel);
        bind.setLifecycleOwner(this);
    }
}


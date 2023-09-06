package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {

    public MutableLiveData<String> mutableLiveData=new MutableLiveData<>();

    private MyModel getData(){
        return new MyModel(
                "Dahlia", 4800,3.8f
        );
    }
    public void getappName(){
        String name= getData().getAppName();
        mutableLiveData.postValue(name);
    }
}

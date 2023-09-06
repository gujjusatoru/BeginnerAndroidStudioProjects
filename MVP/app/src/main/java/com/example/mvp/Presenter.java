package com.example.mvp;

public class Presenter {

    AppView appView;

    public Presenter(AppView appView) {
        this.appView = appView;
    }

    public MyModel getApp(){
        return new MyModel(
                "Dahlia", 4800,3.8f
        );
    }

    public void getappName(){
        appView.onGetAppName(getApp().appName);
    }
}

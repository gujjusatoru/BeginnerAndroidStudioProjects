package com.example.mvvm;

public class MyModel {
    String appName;
    int appDownloads;
    float appRatings;

    public MyModel(String appName, int appDownloads, float appRatings) {
        this.appName = appName;
        this.appDownloads = appDownloads;
        this.appRatings = appRatings;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppDownloads() {
        return appDownloads;
    }

    public void setAppDownloads(int appDownloads) {
        this.appDownloads = appDownloads;
    }

    public float getAppRatings() {
        return appRatings;
    }

    public void setAppRatings(float appRatings) {
        this.appRatings = appRatings;
    }
}

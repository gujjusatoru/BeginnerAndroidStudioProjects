package com.example.advancedrecyclerviews.recyclernormal;

public class Planet {
    private String name;
    private int image;
    private double distanceSun;

    public Planet(String name, int image, double distanceSun) {
        this.name = name;
        this.image = image;
        this.distanceSun=distanceSun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getDistanceSun() {
        return distanceSun;
    }

    public void setDistanceSun(double distanceSun) {
        this.distanceSun = distanceSun;
    }
}

package com.example.advancedrecyclerviews.recyclermanyview;

public class TravelModel {
    private String place;
    private String food;
    private String site;
    private String city;
    private String type;

    public TravelModel(String place, String food) {
        this.place = place;
        this.food = food;
    }

    public TravelModel(String site, String city, String type) {
        this.site = site;
        this.city = city;
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

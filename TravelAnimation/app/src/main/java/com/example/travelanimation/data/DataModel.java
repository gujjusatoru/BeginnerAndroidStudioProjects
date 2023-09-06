package com.example.travelanimation.data;

public class DataModel {
    private String name;
    private String city;
    private int id_obj;
    private int image;

    public DataModel(String name, String version, int id_obj, int image) {
        this.name = name;
        this.city = version;
        this.id_obj = id_obj;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId_obj() {
        return id_obj;
    }

    public void setId_obj(int id_obj) {
        this.id_obj = id_obj;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

package com.example.demoproject.data;

public class DataModel {
    private int id;
    private String name;
    private int drawable;

    public DataModel(int id, String name, int drawable) {
        this.id = id;
        this.name = name;
        this.drawable = drawable;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

}


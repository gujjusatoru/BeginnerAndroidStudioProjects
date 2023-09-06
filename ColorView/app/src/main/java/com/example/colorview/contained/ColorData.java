package com.example.colorview.contained;

public class ColorData {
    private String color_name;
    private int color;

    public ColorData(String color_name, int color) {
        this.color_name = color_name;
        this.color = color;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

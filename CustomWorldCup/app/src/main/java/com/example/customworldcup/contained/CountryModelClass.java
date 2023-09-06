package com.example.customworldcup.contained;

public class CountryModelClass {
    private String name, cupWon;
    private int image;

    public CountryModelClass(String name, String cupWon, int image) {
        this.name = name;
        this.cupWon = cupWon;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCupWon() {
        return cupWon;
    }

    public void setCupWon(String cupWon) {
        this.cupWon = cupWon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

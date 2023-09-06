package com.example.recycler;

public class LangModel {
    private String country_name, language;
    private int flag;

    public LangModel(String country_name, String language, int flag) {
        this.country_name = country_name;
        this.language = language;
        this.flag = flag;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

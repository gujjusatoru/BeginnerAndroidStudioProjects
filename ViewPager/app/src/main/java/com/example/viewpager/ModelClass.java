package com.example.viewpager;

public enum ModelClass {
    RED(R.string.red,R.layout.red_view),
    BLUE(R.string.blue,R.layout.blue_view),
    GREEN(R.string.green,R.layout.green_view);

    private int titleID;
    private int layoutID;

    ModelClass(int titleID, int layoutID) {
        this.titleID = titleID;
        this.layoutID = layoutID;
    }

    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }
}

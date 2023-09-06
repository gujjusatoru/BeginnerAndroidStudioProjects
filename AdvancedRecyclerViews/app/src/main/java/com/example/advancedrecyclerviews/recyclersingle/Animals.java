package com.example.advancedrecyclerviews.recyclersingle;

import java.io.Serializable;

public class Animals implements Serializable {
    private String name;
    private boolean ischecked=false;

    public Animals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}

package com.example.advancedrecyclerviews.recyclermultiple;

import java.io.Serializable;

public class AnimalsMulti implements Serializable {
    private String name;
    private boolean ischecked=false;

    public AnimalsMulti(String name) {
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

package com.example.demoproject.adapter;

import android.view.View;

public interface ClickedItem {
    void onSelected(int type, View view, int pos);
    void longSelected(View view, int pos);
}

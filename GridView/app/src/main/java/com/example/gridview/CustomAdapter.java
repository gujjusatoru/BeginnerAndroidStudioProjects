package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<ModelClass> {
    public CustomAdapter(@NonNull Context context, @NonNull ArrayList<ModelClass> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemview=convertView;
        if (itemview==null){
            itemview= LayoutInflater.from(getContext()).inflate(R.layout.card,parent,false);
        }
        ModelClass modelClass= getItem(position);
        TextView name=itemview.findViewById(R.id.cardtext);
        ImageView img=itemview.findViewById(R.id.cardimg);
        name.setText(modelClass.getName());
        img.setImageResource(modelClass.getImage());
        return itemview;
    }

}

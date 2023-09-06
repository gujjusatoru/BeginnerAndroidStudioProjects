package com.example.colorview.contained;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.colorview.R;

import java.util.ArrayList;

public class AdapterCustom extends ArrayAdapter<ColorData> {
    private ArrayList<ColorData> dataArray;
    Context context;
    public AdapterCustom(ArrayList<ColorData> data,Context context){
        super(context, R.layout.custom_listview, data);
        this.dataArray=data;
        this.context=context;

    }
    private static class ViewHolder{
        TextView color_name;
        ImageView color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ColorData dataModel= getItem(position);

        ViewHolder viewHolder;
        final View result;
        if (convertView == null){
            viewHolder=new ViewHolder();

            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.custom_listview,parent,false);
            viewHolder.color_name=(TextView) convertView.findViewById(R.id.name);
            viewHolder.color=(ImageView) convertView.findViewById(R.id.color);
            result=convertView;
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.color_name.setText(dataModel.getColor_name());
        viewHolder.color.setImageResource(dataModel.getColor());

        return convertView;
    }
}

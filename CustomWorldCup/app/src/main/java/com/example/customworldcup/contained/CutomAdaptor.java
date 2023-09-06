package com.example.customworldcup.contained;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customworldcup.R;
import com.example.customworldcup.contained.CountryModelClass;

import java.util.ArrayList;

public class CutomAdaptor extends ArrayAdapter<CountryModelClass> {
    private ArrayList<CountryModelClass> dataArray;
    Context context;
    public CutomAdaptor(ArrayList<CountryModelClass> data,Context context){
        super(context, R.layout.custom_view, data);
        this.dataArray=data;
        this.context=context;

    }
    private static class ViewHolder{
        TextView count_name, cup_won;
        ImageView flag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CountryModelClass dataModel= getItem(position);

        ViewHolder viewHolder;
        final View result;
        if (convertView == null){
            viewHolder=new ViewHolder();

            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.custom_view,parent,false);
            viewHolder.count_name=(TextView) convertView.findViewById(R.id.countryName);
            viewHolder.cup_won=(TextView) convertView.findViewById(R.id.cupWon);
            viewHolder.flag=(ImageView) convertView.findViewById(R.id.imageView);
            result=convertView;
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.count_name.setText(dataModel.getName());
        viewHolder.cup_won.setText(dataModel.getCupWon());
        viewHolder.flag.setImageResource(dataModel.getImage());

        return convertView;
    }
}

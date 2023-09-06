package com.example.travelanimation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.travelanimation.R;
import com.example.travelanimation.data.DataModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dataSet;

    public MainAdapter(Context context, ArrayList<DataModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text;
        TextView city;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.feature);
            this.city = itemView.findViewById(R.id.city);
            this.image =itemView.findViewById(R.id.imageViewMain);

        }

        @Override
        public void onClick(View v) {

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(dataSet.get(position).getName());
        holder.city.setText(dataSet.get(position).getCity());

        Glide.with(context).load(dataSet.get(position).getImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.image);
    }
}

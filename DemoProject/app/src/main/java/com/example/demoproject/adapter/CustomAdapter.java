package com.example.demoproject.adapter;

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
import com.example.demoproject.R;
import com.example.demoproject.data.DataModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dataSet;
    int itemType, checkPos = -1;
    private ClickedItem clickedItem;

    public CustomAdapter(Context context, ArrayList<DataModel> dataSet, int itemType) {
        this.context = context;
        this.dataSet = dataSet;
        this.itemType = itemType;
    }

    public void setClickedItem(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.textcard);
            this.image = itemView.findViewById(R.id.imagecard);
        }

        void bind(DataModel data) {
            if (checkPos == -1) {
                itemView.setBackground(context.getDrawable(R.drawable.cardrecycler));
                text.setTextColor(context.getColor(R.color.greydark));
            }
            else {
                if (checkPos==getAdapterPosition()){
                    itemView.setBackground(context.getDrawable(R.drawable.selectedcard));
                    text.setTextColor(context.getColor(R.color.white));
                }
                else{
                    itemView.setBackground(context.getDrawable(R.drawable.cardrecycler));
                    text.setTextColor(context.getColor(R.color.greydark));
                }
            }


            text.setText("" + data.getName());
            Glide.with(context).load(data.getDrawable())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (checkPos != getAdapterPosition()) {
                itemView.setBackground(context.getDrawable(R.drawable.selectedcard));
                text.setTextColor(context.getColor(R.color.white));
                notifyItemChanged(checkPos);
                checkPos = getAdapterPosition();
            } else {
                itemView.setBackground(context.getDrawable(R.drawable.cardrecycler));
                text.setTextColor(context.getColor(R.color.greydark));
                checkPos = -1;
                notifyItemChanged(checkPos);
            }
            clickedItem.onSelected(itemType, v, checkPos);
        }
    }
}




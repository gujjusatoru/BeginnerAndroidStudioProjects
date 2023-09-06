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
import com.example.demoproject.activities.Creations;
import com.example.demoproject.data.CreationsModel;
import com.example.demoproject.data.DataModel;

import java.util.ArrayList;

public class CreationsAdapter extends RecyclerView.Adapter<CreationsAdapter.MyView> {
    Context context;
    ArrayList<CreationsModel> creationsModels;
    int checkPos=-1;
    private ClickedItem clickedItem;

    public CreationsAdapter(Context context, ArrayList<CreationsModel> creationsModels) {
        this.context = context;
        this.creationsModels = creationsModels;
    }

    public void setClickedItem(ClickedItem clickedItem){
        this.clickedItem=clickedItem;
    }
    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView text;
        ImageView image;

        public MyView(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.textcard);
            this.image = itemView.findViewById(R.id.imagecard);
        }

        void bind(CreationsModel create) {
            if (checkPos == -1) {
                itemView.setBackground(context.getDrawable(R.drawable.creation_recycler));
                text.setTextColor(context.getColor(R.color.white));
            }
            else {
                if (checkPos==getAdapterPosition()){
                    itemView.setBackground(context.getDrawable(R.drawable.selected_creation_recycler));
                    text.setTextColor(context.getColor(R.color.greydark));
                }
                else{
                    itemView.setBackground(context.getDrawable(R.drawable.creation_recycler));
                    text.setTextColor(context.getColor(R.color.white));
                }
            }


            text.setText("" + create.getName());
            Glide.with(context).load(create.getImage())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (checkPos != getAdapterPosition()) {
                itemView.setBackground(context.getDrawable(R.drawable.selected_creation_recycler));
                text.setTextColor(context.getColor(R.color.greydark));
                notifyItemChanged(checkPos);
                checkPos = getAdapterPosition();
            } else {
                itemView.setBackground(context.getDrawable(R.drawable.creation_recycler));
                text.setTextColor(context.getColor(R.color.white));
                checkPos = -1;
                notifyItemChanged(checkPos);
            }if(checkPos!=-1){
            clickedItem.onSelected(0,v, checkPos);}
        }

        @Override
        public boolean onLongClick(View v) {
            if (checkPos != getAdapterPosition()) {
                itemView.setBackground(context.getDrawable(R.drawable.selected_creation_recycler));
                text.setTextColor(context.getColor(R.color.greydark));
                notifyItemChanged(checkPos);
                checkPos = getAdapterPosition();
            } else {
                itemView.setBackground(context.getDrawable(R.drawable.creation_recycler));
                text.setTextColor(context.getColor(R.color.white));
                checkPos = -1;
                notifyItemChanged(checkPos);
            }
            if(checkPos!=-1)
                clickedItem.longSelected(v, checkPos);
            return true;
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_creations, parent, false);
        MyView myView = new MyView(view);

        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.bind(creationsModels.get(position));
    }

    @Override
    public int getItemCount() {
        return creationsModels.size();
    }


}

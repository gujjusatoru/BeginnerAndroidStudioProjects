package com.example.advancedrecyclerviews.recyclercard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.StoneHolder> {
    private Context context;
    private ArrayList<Stones> stones;

    public CardAdapter(Context context, ArrayList<Stones> stones) {
        this.context = context;
        this.stones = stones;
    }

    public class StoneHolder extends RecyclerView.ViewHolder{
        private TextView namecard;
        private ImageView imgcard;
        public StoneHolder(@NonNull View itemView) {
            super(itemView);
            namecard=itemView.findViewById(R.id.cardname);
            imgcard=itemView.findViewById(R.id.cardimg);

        }
        void SetDetails(Stones stone){
            namecard.setText(stone.getNameStone());
            imgcard.setImageResource(stone.getImgStone());
        }
    }


    @NonNull
    @Override
    public StoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_card,parent,false);
        return new StoneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoneHolder holder, int position) {
        Stones stone=stones.get(position);
        holder.SetDetails(stone);

    }

    @Override
    public int getItemCount() {
        return stones.size();
    }


}

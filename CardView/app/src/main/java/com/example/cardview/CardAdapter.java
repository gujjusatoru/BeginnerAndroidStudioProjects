package com.example.cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyView> {

    //1.Data
    private Context context;

    private OnClick clickedItem;
    private ArrayList<CardDataModel> cardlist;
//    2.Constructor

    public CardAdapter(Context context, ArrayList<CardDataModel> cardlist) {
        this.context = context;
        this.cardlist = cardlist;
    }
    public void setClickedItem(OnClick clickedItem){
        this.clickedItem=clickedItem;
    }

    //3.ViewHolder
    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView cardImg;
        private TextView cardName;

        public MyView(@NonNull View itemView) {
            super(itemView);
            cardImg= itemView.findViewById(R.id.imagecard);
            cardName=itemView.findViewById(R.id.namecard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickedItem!=null)
                clickedItem.onClick(v,getAdapterPosition());

        }

    }
    //4.Implement methods of CardAdapter class

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lay=LayoutInflater.from(parent.getContext());
        View thisView= lay.inflate(R.layout.custom_card,parent,false);
        MyView myView=new MyView(thisView);
        return myView;
        //this creates the View
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        CardDataModel cardModel= cardlist.get(position);
        holder.cardName.setText(cardModel.getCardName());
        holder.cardImg.setImageResource(cardModel.getCardImg());
        if(cardModel.isSelect()){
            holder.itemView.setBackgroundResource(R.drawable.cardbg);
        }
        else holder.itemView.setBackgroundResource(R.drawable.white);

        //this shows the View
    }

    @Override
    public int getItemCount() {
        return cardlist.size();
        //this gives size of the list
    }




}

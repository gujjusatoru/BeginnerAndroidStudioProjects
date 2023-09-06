package com.example.foodcart;

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

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyView> {

    //1.Data
    private Context context;
    private boolean cart;

    private OnClick clickedItem;
    private ArrayList<CardDataModel> cardlist;
//    2.Constructor

    public AdapterClass(Context context, ArrayList<CardDataModel> cardlist,boolean cart) {
        this.context = context;
        this.cardlist = cardlist;
        this.cart=cart;
    }
    public void setClickedItem(OnClick clickedItem){
        this.clickedItem=clickedItem;
    }

    //3.ViewHolder
    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView cardImg;
        private TextView cardName, cardCount;

        public MyView(@NonNull View itemView) {
            super(itemView);
            cardImg= itemView.findViewById(R.id.imagecard);
            cardName=itemView.findViewById(R.id.namecard);
            cardCount=itemView.findViewById(R.id.count);
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
        if(cart){
            if(cardModel.isSelect()){
                holder.cardName.setText(cardModel.getCardName());
                holder.cardImg.setImageResource(cardModel.getCardImg());
                holder.itemView.setBackgroundResource(R.drawable.white);
                holder.cardCount.setVisibility(View.VISIBLE);
            }
        }
        else{

        holder.cardName.setText(cardModel.getCardName());
        holder.cardImg.setImageResource(cardModel.getCardImg());
        if(cardModel.isSelect()){
            holder.itemView.setBackgroundResource(R.drawable.cardbg);
        }
        else holder.itemView.setBackgroundResource(R.drawable.white);}

        //this shows the View
    }

    @Override
    public int getItemCount() {
        return cardlist.size();
        //this gives size of the list
    }




}


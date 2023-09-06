package com.example.advancedrecyclerviews.recyclermanyview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class ManyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int typeFood=1,typePlace=2;
    private Context context;
    private ArrayList<TravelModel> travelModels;

    public ManyViewAdapter(Context context, ArrayList<TravelModel> travelModels) {
        this.context = context;
        this.travelModels = travelModels;
    }



    class PlaceViewHold extends RecyclerView.ViewHolder{
        TextView site,city,type;

        public PlaceViewHold(@NonNull View itemView) {
            super(itemView);
            site=itemView.findViewById(R.id.namesite);
            city=itemView.findViewById(R.id.namecity);
            type=itemView.findViewById(R.id.sitetype);
        }
        void setPlaceDetails(TravelModel placeDetails){
            site.setText(placeDetails.getSite());
            city.setText(placeDetails.getCity());
            type.setText(placeDetails.getType());
        }
    }
    class FoodViewHold extends RecyclerView.ViewHolder{
        TextView place,food;

        public FoodViewHold(@NonNull View itemView) {
            super(itemView);
            place=itemView.findViewById(R.id.namemany);
            food=itemView.findViewById(R.id.itemmany);
        }
        void setFoodDetails(TravelModel foodDetails){
            place.setText(foodDetails.getPlace());
            food.setText("Known for: "+foodDetails.getFood());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(travelModels.get(position).getCity())){
            return typeFood;
        }
        else return typePlace;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==typeFood){
            view= LayoutInflater.from(context).inflate(R.layout.fooditem,parent,false);
            return new FoodViewHold(view);
        }
        else{
            if(viewType==typePlace) {
                view = LayoutInflater.from(context).inflate(R.layout.placeitem, parent, false);
                return new PlaceViewHold(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==typeFood){
            ((FoodViewHold)holder).setFoodDetails(travelModels.get(position));
        }
        else {
            ((PlaceViewHold)holder).setPlaceDetails(travelModels.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return travelModels.size();
    }
}

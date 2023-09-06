package com.example.advancedrecyclerviews.recyclernormal;

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
import java.util.Locale;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.MyView> {
    private Context context;
    private ArrayList<Planet> planets;

    public NormalAdapter(Context context, ArrayList<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }


    public class MyView extends RecyclerView.ViewHolder{
        private TextView name, distance;
        private ImageView image;

        public MyView(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.planetname);
            distance=itemView.findViewById(R.id.distance);
            image=itemView.findViewById(R.id.normal_img);

        }
        void setDetails(Planet planet){
            name.setText(planet.getName());
            distance.setText(String.format(Locale.US,"Distance from Sun: "+planet.getDistanceSun()));
            image.setImageResource(planet.getImage());
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.normal_list_item,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Planet planet=planets.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }
}

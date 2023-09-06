package com.example.advancedrecyclerviews.recyclersingle;

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

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ViewSingle> {
    private Context context;
    private ArrayList<Animals> animals;
    private int checkPos=-1;

    public SingleAdapter(Context context, ArrayList<Animals> animals) {
        this.context = context;
        this.animals = animals;
    }


    public class ViewSingle extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView check;


        public ViewSingle(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.singlename);
            check=itemView.findViewById(R.id.sign);
        }
        void bind(final Animals animal){
            if(checkPos==-1) {
                check.setVisibility(View.GONE);
            }
                else{
                    if(checkPos==getAdapterPosition()){
//                        check.setVisibility(View.VISIBLE);
                    }
                    else check.setVisibility(View.GONE);
                }

                name.setText(""+animal.getName());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check.setVisibility(View.VISIBLE);
                        if(checkPos!=getAdapterPosition()){
                            notifyItemChanged(checkPos);
                            checkPos=getAdapterPosition();
                        }
                    }
                });
        }
    }
    public Animals selected(){
        if(checkPos!=-1){
            return animals.get(checkPos);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewSingle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewe= LayoutInflater.from(context).inflate(R.layout.singlecustom,parent,false);
        return new ViewSingle(viewe);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSingle holder, int position) {
        holder.bind(animals.get(position));

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }


}

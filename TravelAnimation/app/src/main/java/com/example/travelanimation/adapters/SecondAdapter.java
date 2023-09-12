package com.example.travelanimation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.travelanimation.OnClick;
import com.example.travelanimation.R;
import com.example.travelanimation.data.DataModel;
import com.example.travelanimation.data.DataSender3;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.MyViewHolder> {
    private WeakReference<Context> context;
    private ArrayList<DataModel> dataSet;
    private int viewType;
    public String getType;
    private OnClick clicked;
    View view;

    public SecondAdapter(Context context, ArrayList<DataModel> dataSet, int viewType) {
        this.context=new WeakReference<>(context);
        this.dataSet=dataSet;
        this.viewType=viewType;
    }

    public void setClicked(OnClick clicked) {
        this.clicked = clicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context != null && context.get() != null){
            view = LayoutInflater.from(context.get()).inflate(R.layout.card_secondary, parent, false);


    }
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.city.setText(dataSet.get(position).getName());
        holder.state.setText(dataSet.get(position).getCity());
        Glide.with(context.get()).load(dataSet.get(position).getImage())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView city,state;
        ImageView image;
        Button viewBtn;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            this.city=itemView.findViewById(R.id.city);
            this.state=itemView.findViewById(R.id.state);
            this.image=itemView.findViewById(R.id.imageViewSecond);
            this.viewBtn=itemView.findViewById(R.id.viewbtn);
            if(viewType==1) {
                itemView.setOnClickListener(this);
            }
            if(viewType==2){
                viewBtn.setVisibility(View.GONE);
                state.setVisibility(View.VISIBLE);
                itemView.setOnClickListener(this);
            }

        }


        @Override
        public void onClick(View v) {
            if(viewType==1) {
                getType = city.getText().toString();
                for (int i = 0; i < dataSet.size(); i++) {
                    if (getType == dataSet.get(i).getName()) {
                        clicked.btnClick(getType);
                    }
                }
            }
            clicked.onSelect(v,getAdapterPosition(),viewType);

        }
    }
}

package com.example.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyView> {
    private OnClickListener clickedItem;
    public void setClickedItem(OnClickListener clickedItem) {
        this.clickedItem = clickedItem;
    }





    private LangModel[] datalist;
    public MyAdapter(LangModel[] datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflateView= LayoutInflater.from(parent.getContext());
        View listItem=inflateView.inflate(R.layout.recycle_customlayout,parent,false);
        MyView myView= new MyView(listItem);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        final LangModel mylist=datalist[position];
        holder.name_txt.setText(mylist.getCountry_name());
        holder.img_vw.setImageResource(mylist.getFlag());
    }

    @Override
    public int getItemCount() {
        return datalist.length;
    }


    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img_vw;
        public TextView name_txt;

        public MyView(@NonNull View itemView) {
            super(itemView);
            this.img_vw=itemView.findViewById(R.id.imageView);
            this.name_txt=itemView.findViewById((R.id.countryname));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickedItem!=null){
                clickedItem.OnClick(v,getAdapterPosition());
        }
    }


    }

}

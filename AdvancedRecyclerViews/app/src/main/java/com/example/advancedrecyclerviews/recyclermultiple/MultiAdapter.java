package com.example.advancedrecyclerviews.recyclermultiple;

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

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiView> {

    private ArrayList<AnimalsMulti> animalArray;
    private Context context;


    public MultiAdapter(ArrayList<AnimalsMulti> animalArray, Context context) {
        this.animalArray = animalArray;
        this.context = context;
    }



    class MultiView extends RecyclerView.ViewHolder{
        TextView name;
        ImageView check;
        public MultiView(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.multiname);
            check=itemView.findViewById(R.id.check);
        }
        void bind(final AnimalsMulti animal){
            check.setVisibility(animal.isIschecked()?View.VISIBLE:View.GONE);
            name.setText(animal.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animal.setIschecked(!animal.isIschecked());
                    check.setVisibility(animal.isIschecked()?View.VISIBLE:View.GONE);
                }
            });
        }
    }
    public ArrayList<AnimalsMulti> getAll(){return animalArray;}


    public ArrayList<AnimalsMulti> selected() {
        ArrayList<AnimalsMulti> selectList=new ArrayList<>();
        for (int i = 0; i < animalArray.size();i++){
            if(animalArray.get(i).isIschecked()){
                selectList.add(animalArray.get(i));
            }
        }
        return selectList;
    }
    @NonNull
    @Override
    public MultiView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.multiitem,parent,false);
        return new MultiView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiView holder, int position) {
        holder.bind(animalArray.get(position));

    }

    @Override
    public int getItemCount() {
        return animalArray.size();
    }
}

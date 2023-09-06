package com.example.advancedrecyclerviews.recyclerswipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apachat.swipereveallayout.core.SwipeLayout;
import com.apachat.swipereveallayout.core.ViewBinder;
import com.example.advancedrecyclerviews.R;

import java.util.ArrayList;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeView> {

    private Context context;
    private ArrayList<Spotify> spotifyPlaylist;
    private final ViewBinder binder=new ViewBinder();

    public SwipeAdapter(Context context, ArrayList<Spotify> spotifyPlaylist) {
        this.context = context;
        this.spotifyPlaylist = spotifyPlaylist;
    }

    class SwipeView extends RecyclerView.ViewHolder{
        TextView name, singer;
        ImageView albumimg, like, liked, delete;
        SwipeLayout swipeLayout;

        public SwipeView(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameswipe);
            singer=itemView.findViewById(R.id.byswipe);
            albumimg=itemView.findViewById(R.id.swipeimg);
            like=itemView.findViewById(R.id.like);
            liked=itemView.findViewById(R.id.liked);
            delete=itemView.findViewById(R.id.delete);
            swipeLayout=itemView.findViewById(R.id.swipelay);

        }
        void bindData(Spotify spot){
            liked.setVisibility(spot.isChecked()?View.VISIBLE:View.GONE);
            name.setText(spot.getNameSong());
            singer.setText(spot.getSongBy());
            albumimg.setImageResource(spot.getAlbumImg());
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spot.setChecked(!spot.isChecked());
                    liked.setVisibility(spot.isChecked()?View.VISIBLE:View.GONE);
                    binder.closeLayout(String.valueOf(spot.getNameSong()));

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spotifyPlaylist.remove(spot);
                    notifyDataSetChanged();
                }
            });
            liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spot.setChecked(!spot.isChecked());
                    liked.setVisibility(spot.isChecked()?View.VISIBLE:View.GONE);
                }
            });
        }
    }
    public ArrayList<Spotify> likedSongs(){
        ArrayList<Spotify> likedPlaylist=new ArrayList<>();
        for(int i=0;i<spotifyPlaylist.size();i++){
            if(spotifyPlaylist.get(i).isChecked()){
                likedPlaylist.add(spotifyPlaylist.get(i));
            }
        }
        return likedPlaylist;
    }

    @NonNull
    @Override
    public SwipeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemswipe,parent,false);
        return new SwipeView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeView holder, int position) {
        binder.setOpenOnlyOne(true);
        binder.bind(holder.swipeLayout,String.valueOf(spotifyPlaylist.get(position).getNameSong()));
        binder.closeLayout(String.valueOf(spotifyPlaylist.get(position).getNameSong()));
        holder.bindData(spotifyPlaylist.get(position));

    }

    @Override
    public int getItemCount() {
        return spotifyPlaylist.size();
    }
}

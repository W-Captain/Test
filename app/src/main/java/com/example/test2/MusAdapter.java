package com.example.test2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusAdapter extends RecyclerView.Adapter<MusAdapter.ViewHolder> {
    private ArrayList<MusicPicture> myPictures;
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView picImg;
        TextView picText;
        int d_id;
        int d_resource;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picImg=(ImageView)itemView.findViewById(R.id.pic_image);
            picText=(TextView)itemView.findViewById(R.id.pic_text);
            view=itemView;
        }
    }

    public MusAdapter(ArrayList<MusicPicture> pictures) {
        myPictures=pictures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MusicPicture musicPicture=myPictures.get(position);
        holder.picImg.setImageResource(musicPicture.getPic_id());
        holder.picText.setText(musicPicture.getPic_name());
        holder.d_id=musicPicture.getPic_id();
        holder.d_resource=musicPicture.getPic_resource();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),MusActivity.class);
                intent.putExtra("ziyuan",holder.d_resource);
                intent.putExtra("tupian",holder.d_id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPictures.size();
    }
}

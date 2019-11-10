package com.example.test2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VidAdapter2 extends RecyclerView.Adapter<VidAdapter2.ViewHolder>{
    private ArrayList<VideoPicture> videoPictures;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item2,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    public VidAdapter2(ArrayList<VideoPicture> videoPictures) {
        this.videoPictures=videoPictures;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        VideoPicture videoPicture=videoPictures.get(position);
        holder.picImg.setImageResource(videoPicture.getPic_id());
        holder.picText.setText(videoPicture.getPic_name());
        holder.resource=videoPicture.getPic_string();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),VidActivity.class);
                intent.putExtra("shipin",holder.resource);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoPictures.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView picImg;
        TextView picText;
        String resource;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picImg=(ImageView)itemView.findViewById(R.id.pic_image2);
            picText=(TextView)itemView.findViewById(R.id.pic_text2);
            view=itemView;
        }
    }
}

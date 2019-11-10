package com.example.test2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {
    private ArrayList<Picture> myPictures;

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView picImg;
        TextView picText;
        int d_id;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picImg=(ImageView)itemView.findViewById(R.id.pic_image);
            picText=(TextView)itemView.findViewById(R.id.pic_text);
            view=itemView;
        }
    }

    public PicAdapter(ArrayList<Picture> pictures) {
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
        Picture picture=myPictures.get(position);
        holder.picImg.setImageResource(picture.getPic_id());
        holder.picText.setText(picture.getPic_name());
        holder.d_id=picture.getPic_id();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),PicActivity.class);
                int data=holder.d_id;
                intent.putExtra("drawable_id",data);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPictures.size();
    }
}

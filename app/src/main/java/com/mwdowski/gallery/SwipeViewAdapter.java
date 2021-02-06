package com.mwdowski.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SwipeViewAdapter extends RecyclerView.Adapter<SwipeViewAdapter.SwipeViewHolder> {

    private ArrayList<Photo> photos;
    private Context context;

    public SwipeViewAdapter(ArrayList<Photo> data, Context context) {
        this.photos = data;
        this.context = context;
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_photo_layout, parent, false);
        return new SwipeViewAdapter.SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        holder.image.setImageURI(photos.get(position).uri);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class SwipeViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public SwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.swipeableImage);
        }
    }
}

package com.mwdowski.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<Photo> photos;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public RecyclerViewAdapter(ArrayList<Photo> data, Context context) {
        this.photos = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        setOnClickListener(holder, position);
        setEmptyThumbnail(holder, position);
        setSubtext(holder, position);
        setImage(holder, position);
    }

    private void setOnClickListener(@NotNull RecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SwipeActivity.class);
                intent.putExtra("image_index", position);
                context.startActivity(intent);
            }
        });
    }

    private void setSubtext(@NotNull RecyclerViewHolder holder, int position) {
        holder.description.setText(photos.get(position).name);
    }


    private void setEmptyThumbnail(@NotNull RecyclerViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.empty_thumbnail);
    }

    private void setImage(@NotNull RecyclerViewHolder holder, int position) {
        holder.image.post(new Runnable() {
            @Override
            public void run() {
                holder.image.setImageResource(R.drawable.empty_thumbnail);

                if (holder.isViewVisible(position)) {
                    // generate thumbnail from photo
                    Bitmap thumbnail = photos.get(position).generateThumbnail(context);
                    // set image thumbnail
                    holder.image.setImageBitmap(thumbnail);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.subtext);
            image = itemView.findViewById(R.id.imageView);
        }

        private boolean isViewVisible(int position) {
            return (layoutManager.findFirstVisibleItemPosition() <= position &&
                    layoutManager.findLastVisibleItemPosition() >= position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        this.layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }
}

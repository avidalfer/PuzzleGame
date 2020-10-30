package com.example.puzzlegame.ui.gallery;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Image;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private List<Image> galleryImages = new ArrayList<>();
    private OnImageListener onImageListener;

    public GalleryAdapter(List<Image> galleryImages, OnImageListener onImageListener) {
        this.galleryImages = galleryImages;
        this.onImageListener = onImageListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup galleryView = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gallery_adapter, parent, false);
        return new MyViewHolder(galleryView, onImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(galleryImages.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    // ViewHolder Class
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imageView;
        private final OnImageListener onImageListener;

        public MyViewHolder(@NonNull ViewGroup viewGroup, OnImageListener onImageListener) {
            super(viewGroup);
            this.imageView = viewGroup.findViewById(R.id.gridImageview);
            this.onImageListener = onImageListener;

            imageView.setOnClickListener(this);
        }

        public void bind(Image image) {
            imageView.setImageBitmap(image.getBitmap());
        }

        @Override
        public void onClick(View v) {
            onImageListener.onImageClick(getAdapterPosition());
        }
    }

    // OnClickListener interface
    public interface OnImageListener{
        void onImageClick(int position);
    }

}

package com.example.puzzlegame.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Image;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private final List<Image> galleryImages;
    private final OnImageListener onImageListener;
    private final Context context;

    public GalleryAdapter(Context context, List<Image> galleryImages, OnImageListener onImageListener) {
        this.galleryImages = galleryImages;
        this.onImageListener = onImageListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup galleryView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.activity_gallery_adapter, parent, false);
        return new MyViewHolder(galleryView, onImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(galleryImages.get(position));
    }

    @Override
    public int getItemCount() {
        if (galleryImages == null){
            return 0;
        }
        return this.galleryImages.size();
    }

    // ViewHolder Class
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imageView;
        private final OnImageListener onImageListener;

        public MyViewHolder(@NonNull View view, OnImageListener onImageListener) {
            super(view);
            this.imageView = view.findViewById(R.id.gridImageview);
            this.onImageListener = onImageListener;

            imageView.setOnClickListener(this);
        }

        public void bind(Image img) {
            imageView.setImageBitmap(img.getBitmap());
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

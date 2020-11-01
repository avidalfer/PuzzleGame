package com.example.puzzlegame.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private final AssetManager am;
    private List<Image> galleryImages;
    private final OnImageListener onImageListener;
    private final Context context;

    public GalleryAdapter(Context context, List<Image> galleryImages, OnImageListener onImageListener, AssetManager am) {
        this.galleryImages = galleryImages;
        this.onImageListener = onImageListener;
        this.context = context;
        this.am = am;
    }

    public void setGalleryImages(List<Image> images) {
        this.galleryImages = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup galleryView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.activity_gallery_adapter, parent, false);
        return new MyViewHolder(galleryView, onImageListener, am);
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
        private final AssetManager am;
        private Image image;

        public MyViewHolder(@NonNull View view, OnImageListener onImageListener, AssetManager am) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.gridImageview);
            this.onImageListener = onImageListener;
            this.am = am;

            imageView.setOnClickListener(this);
        }

        public void bind(Image img) {
            this.image = img;
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

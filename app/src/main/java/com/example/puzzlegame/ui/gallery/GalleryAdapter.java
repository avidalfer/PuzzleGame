package com.example.puzzlegame.ui.gallery;

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

    private List<Image> galleryImages;

    public GalleryAdapter(List<Image> galleryImages) {
        this.galleryImages = galleryImages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup galleryView = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gallery_adapter, parent, false);
        return new MyViewHolder(galleryView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(galleryImages.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ViewGroup viewGroup;
        private ImageView imageView;

        public MyViewHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup);
            this.viewGroup = viewGroup;
            this.imageView = viewGroup.findViewById(R.id.);
        }

        public void bind(Image image) {
            imageView
        }
    }
}

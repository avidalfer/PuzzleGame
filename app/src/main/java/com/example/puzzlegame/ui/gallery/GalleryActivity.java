package com.example.puzzlegame.ui.gallery;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class GalleryActivity extends BaseActivity {

    private GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        setViews();
    }

    private void setViews() {
        galleryViewModel = new GalleryViewModel();
        RecyclerView galleryGridView = findViewById(R.id.gridGallery);
        galleryGridView.setHasFixedSize(true);

        setListeners();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        galleryGridView.setLayoutManager(layoutManager);

        showGallery();
    }

    private void setListeners() {
        //listener para el botón carrete
        //listener para el botón cámara

        //LiveData
        final Observer<List<Image>> galleryImagesObserver = new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                galleryViewModel.addImageToGallery(images);
            }
        };
        galleryViewModel.getGalleryImages().observe(this, galleryImagesObserver);
    }

    private void showGallery() {
        List<Image> galleryImages = galleryViewModel.getGalleryImages().getValue();
        RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter = new GalleryAdapter(galleryImages);
    }
}
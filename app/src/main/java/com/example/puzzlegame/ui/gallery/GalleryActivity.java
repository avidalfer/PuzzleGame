package com.example.puzzlegame.ui.gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.ui.PuzzleGameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameAdapter;

import java.util.List;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.OnImageListener {

    private GalleryViewModel galleryViewModel;
    private Level levelSelected;
    private List<Image> galleryImages;
    RecyclerView galleryGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        levelSelected = (Level) intent.getSerializableExtra("levelSelected");
        setViews();
    }

    private void setViews() {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        galleryGridView = findViewById(R.id.gridGallery);
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
                showGallery();
            }
        };
        galleryViewModel.getGalleryImages(this).observe(this, galleryImagesObserver);
    }

    private void showGallery() {
        RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter = new GalleryAdapter(galleryImages, this);
        galleryGridView.setAdapter(adapter);
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(this, PuzzleGameActivity.class);
        intent.putExtra("bgImage", galleryImages.get(position));
        intent.putExtra("gameLevel", levelSelected);
        startActivity(intent);
    }
}
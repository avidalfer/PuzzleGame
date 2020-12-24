package com.example.puzzlegame.ui.gallery;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.OnImageListener {

    private GalleryViewModel galleryViewModel;
    private Level levelSelected;
    private List<Image> galleryImages = new ArrayList<>();
    private RecyclerView galleryGridView;
    private RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter;
    AssetManager assetManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);
//prueba de git
        init();
        setViews(); // and listeners
    }

    private void init() {
        assetManager = getAssets();

        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        galleryImages = galleryViewModel.getImageList().getValue();

        Intent intent = getIntent();
        levelSelected = (Level) intent.getSerializableExtra("levelSelected");
    }

    private void setViews() {
        galleryGridView = findViewById(R.id.gridGallery);
        galleryGridView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 3);
        galleryGridView.setLayoutManager(layoutManager);

        setListeners();

        galleryViewModel.updateGallery(assetManager);
    }

    private void setListeners() {
        //listener para el botón carrete
        //listener para el botón cámara

        //LiveData
        final Observer<List<Image>> galleryImagesObserver = new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                if (galleryImages == null) {
                    galleryImages = images;
                }
                    inflateGallery();
                    adapter.notifyDataSetChanged();
                }
        };
        galleryViewModel.getImageList().observe(this, galleryImagesObserver);
    }

    private void inflateGallery() {
        adapter = new GalleryAdapter(this, galleryImages, this);
        galleryGridView.setAdapter(adapter);
    }

    @Override
    public void onImageClick(int position) {
        Intent intent = new Intent(this, PuzzleGameActivity.class);
        galleryViewModel.setImageToBackground(galleryImages.get(position));
        intent.putExtra("gameLevel", levelSelected);
        startActivity(intent);
    }
}
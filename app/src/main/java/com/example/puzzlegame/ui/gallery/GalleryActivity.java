package com.example.puzzlegame.ui.gallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.OnImageListener {

    static final int REQUEST_READ_EXTERNAL_STORAGE = 5;
    static final int REQUEST_IMAGE_GALLERY = 2;

    private GalleryViewModel galleryViewModel;
    private Level levelSelected;
    private List<Image> galleryImages = new ArrayList<>();
    private RecyclerView galleryGridView;
    private RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter;
    private FloatingActionButton addFromGalleryButton;
    AssetManager assetManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

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
        addFromGalleryButton = findViewById(R.id.addImageButton);
        setListeners();
        galleryViewModel.updateGallery(assetManager);
    }

    private void setListeners() {
        addFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getImageFromGallery();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.not_available), Toast.LENGTH_LONG).show();
                }
            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getImageFromGallery() {
        onRequestPermissionsResult(REQUEST_READ_EXTERNAL_STORAGE, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new int[]{PackageManager.PERMISSION_GRANTED});
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            galleryViewModel.addImage(uri);
        }
    }
}
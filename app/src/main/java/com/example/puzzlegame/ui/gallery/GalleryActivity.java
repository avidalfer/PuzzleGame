package com.example.puzzlegame.ui.gallery;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.BuildConfig;
import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.OnImageListener {

    static final int REQUEST_IMAGE_GALLERY = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final String TAG = "galleryAct";

    private GalleryViewModel galleryViewModel;
    private Level levelSelected;
    private List<Image> galleryImages = new ArrayList<>();
    private RecyclerView galleryGridView;
    private RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter;
    private FloatingActionButton addFromGalleryButton;
    private FloatingActionButton addFromCameraButton;
    private Uri currentPhotoUri;

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
        addFromGalleryButton = findViewById(R.id.addImageFromLib);
        addFromCameraButton = findViewById(R.id.addImageFromCam);
        setListeners();
        galleryViewModel.updateGallery(this);
    }

    private void setListeners() {
        addFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getImageFromGallery();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.not_available), Toast.LENGTH_LONG).show();
                }
            }
        });

        addFromCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getImageFromCamera();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.not_available), Toast.LENGTH_LONG).show();
                }
            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getImageFromCamera() {
        if (!hasDeviceCamera()) {
            return;
        }
        if (Utils.hasCameraPermission(this)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                File photoFile = null;
                photoFile = createImageFile();

                if (photoFile != null) {
                    currentPhotoUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".provider", photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            } catch (ActivityNotFoundException e) {
                Log.d(TAG, "getImageFromCamera: Another fucking problem");
            }
        }
    }

    private boolean hasDeviceCamera() {
        return (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private File createImageFile() {
        if (Utils.hasWritePermission(this)) {
            @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File imageFile = null;
            try {
                imageFile = File.createTempFile(
                        imageFileName,
                        ".jpg",
                        storageDir
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageFile;
        }

        return null;
    }

    private void inflateGallery() {
        adapter = new GalleryAdapter(this, galleryImages, this);
        galleryGridView.setAdapter(adapter);
    }

    @Override
    public void onImageClick(int position) {
        playGame(galleryImages.get(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getImageFromGallery() {
        if (Utils.hasWritePermission(this) && Utils.hasReadPermission(this)) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Image img = galleryViewModel.addImage(this, uri);
            playGame(img);
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try{
                Image img = galleryViewModel.addImage(this, currentPhotoUri);
                if (img.getBitmap() == null) {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), currentPhotoUri);
                    img.setBitmap(bitmap);
                }
                playGame(img);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void playGame(Image img) {
        Intent intent = new Intent(this, PuzzleGameActivity.class);
        galleryViewModel.setImageToBackground(this, img);
        intent.putExtra("gameLevel", levelSelected);
        startActivity(intent);
    }
}
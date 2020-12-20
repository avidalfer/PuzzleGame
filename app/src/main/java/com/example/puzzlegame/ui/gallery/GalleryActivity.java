package com.example.puzzlegame.ui.gallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.OnImageListener {

    private GalleryViewModel galleryViewModel;
    private Level levelSelected;
    private Image imagen;
    private List<Image> galleryImages = new ArrayList<>();
    private RecyclerView galleryGridView;
    private RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter;
        private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";
    final int COD_SELECCIONA= 10;
    final int COD_FOTO= 20;
    AssetManager assetManager;
    String path;

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

        setListeners();

        galleryViewModel.updateGallery(assetManager);
    }

    private void setListeners() {
    }

    public void setListeners(View view) {
        //listener para el botón carrete
        cargarImagen();

        //listener para el botón cámara
        hacerFoto();

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

    private void hacerFoto(){
        File fileImagen=new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";

        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }
        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/100)+"jpg";
        }

        path= Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        startActivityForResult(intent, COD_FOTO);

    }

    private void cargarImagen() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("imagen/");
        startActivityForResult(intent.createChooser(intent, "Selecciona la aplicación"), COD_SELECCIONA);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }
}
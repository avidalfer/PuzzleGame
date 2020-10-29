package com.example.puzzlegame.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private RecyclerView galleryGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        setViews();
    }

    private void setViews() {

        galleryViewModel = new GalleryViewModel();
        galleryGridView = findViewById(R.id.gridGallery);
        galleryGridView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        galleryGridView.setLayoutManager(layoutManager);

        showGallery();
    }

    private void showGallery() {

        Gallery gallery = galleryViewModel.getGallery().getValue();
        assert gallery != null;
        List<Image> galleryImages = gallery.getImageList();
        RecyclerView.Adapter<GalleryAdapter.MyViewHolder> adapter = new GalleryAdapter(galleryImages);
    }
}
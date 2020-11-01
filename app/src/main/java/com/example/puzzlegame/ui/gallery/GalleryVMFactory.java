package com.example.puzzlegame.ui.gallery;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GalleryVMFactory implements ViewModelProvider.Factory {
    AssetManager am;

    public GalleryVMFactory(AssetManager am) {
        this.am = am;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        GalleryViewModel gVM = new GalleryViewModel(am);
        return (T) gVM;
    }
}

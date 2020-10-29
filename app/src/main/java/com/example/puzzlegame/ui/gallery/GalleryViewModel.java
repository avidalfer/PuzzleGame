package com.example.puzzlegame.ui.gallery;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;

import java.util.List;
import java.util.Objects;

import javax.net.ssl.SSLSession;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<Gallery> gallery;
    private MutableLiveData<List<Image>> galleryImages;

    public GalleryViewModel() {
        gallery = new MutableLiveData<>();
        galleryImages = new MutableLiveData<>();
    }

    public LiveData<Gallery> getGallery() {
        return gallery;
    }
        // get gallery from repository

    public LiveData<List<Image>> getGalleryImages() {
        try {
            galleryImages.setValue(Objects.requireNonNull(gallery.getValue()).getImageList());
            return galleryImages;
        } catch (NullPointerException e) {
            Debug.logStack("GalleryViewModelError", "Null Gallery object", 1);
        }
        return null;
    }
}
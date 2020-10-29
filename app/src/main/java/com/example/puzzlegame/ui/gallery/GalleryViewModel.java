package com.example.puzzlegame.ui.gallery;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<Gallery> gallery;
    private MutableLiveData<List<Image>> galleryImages;
    private final GameAppRepository gameAppRepository = GameAppRepository.getGameAppRepository();

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
            if (galleryImages == null) {
                galleryImages = new MutableLiveData<List<Image>>();
            }
            galleryImages.setValue(gallery.getGalleryFromRepository()).getImageList();
            return galleryImages;
        } catch (NullPointerException e) {
            Debug.logStack("GalleryViewModelError", "Null Gallery object", 1);
        }
        return null;
    }

    private List<Image> getGalleryFromRepository () {
        return gameAppRepository.getGallery();
    }
    public void addImageToGallery(List<Image> images) {
    }
}
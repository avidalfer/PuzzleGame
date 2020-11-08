package com.example.puzzlegame.ui.gallery;

import android.content.res.AssetManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.repository.GalleryRepository;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<List<Image>> galleryImages;
    private final GalleryRepository galleryRepository;

    public GalleryViewModel() {
        galleryRepository = GalleryRepository.getGalleryRepository();
        galleryImages = new MutableLiveData<>();
    }

    public void updateGallery(AssetManager assetManager) {
        galleryRepository.updateImageList(assetManager, true);
        galleryImages.postValue(galleryRepository.getImageList());
    }

    public void setImageToBackground(Image image) {
        galleryRepository.setCurrentBGBitmap(image);
    }

    public LiveData<List<Image>> getImageList() {
        return galleryImages;
    }
}
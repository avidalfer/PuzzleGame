package com.example.puzzlegame.ui.gallery;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameAppRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<List<Image>> galleryImages;
    private final AssetManager assetManager;
    private final GalleryRepository galleryRepository = GalleryRepository.getGalleryRepository();

    public GalleryViewModel(AssetManager am) {
        galleryImages = new MutableLiveData<>();
        assetManager = am;
        init();
    }

    private void init() {
        getImageList();
    }

    private void updateGallery(AssetManager assetManager) {
        galleryRepository.updateImageList(assetManager);
        galleryImages.postValue(galleryRepository.getImageList());
    }

    private void getImageList() {
        List<Image> tempImageList = galleryRepository.getImageList();
        galleryImages.postValue(tempImageList);
    }

    public MutableLiveData<List<Image>> getImageListObserver() {
        return galleryImages;
    }

    public void saveImageList(List<Image> images) {
        galleryRepository.setImageList(images);
        galleryImages.postValue(images);
    }

    public void setImageToBackground(Image image) {
        galleryRepository.setCurrentBGBitmap(image);
    }
}
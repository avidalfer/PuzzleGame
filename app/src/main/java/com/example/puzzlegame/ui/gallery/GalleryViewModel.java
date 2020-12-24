package com.example.puzzlegame.ui.gallery;

import android.content.res.AssetManager;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.common.Utils;
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
        updateGallery();
    }

    private void updateGallery() {
        galleryImages.postValue(galleryRepository.getImageList());
    }

    public void addImage(Uri imageUri){
        Image img = Utils.createImage(imageUri.toString());
        galleryRepository.addImage(img);
        updateGallery();
    }

    public void setImageToBackground(Image image) {
        galleryRepository.setCurrentBGBitmap(image);
    }

    public LiveData<List<Image>> getImageList() {
        return galleryImages;
    }
}
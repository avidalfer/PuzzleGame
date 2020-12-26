package com.example.puzzlegame.ui.gallery;

import android.app.Activity;
import android.net.Uri;

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

    public void updateGallery(Activity act) {
        galleryRepository.updateImageList(act, true);
        updateGallery();
    }

    private void updateGallery() {
        galleryImages.setValue(galleryRepository.getImageList());
    }

    public Image addImage(Activity act, Uri uri){
        Image img = galleryRepository.addImageToList(act, uri);
        updateGallery();
        return img;
    }

    public void setImageToBackground(Activity act, Image image) {
        galleryRepository.setCurrentBGBitmap(act, image);
    }

    public LiveData<List<Image>> getImageList() {
        return galleryImages;
    }
}
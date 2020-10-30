package com.example.puzzlegame.ui.gallery;

import android.content.Context;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.repository.GameAppRepository;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<Gallery> gallery;
    private MutableLiveData<List<Image>> galleryImages;
    private final GameAppRepository gameAppRepository = GameAppRepository.getGameAppRepository();

    public GalleryViewModel() {
        gallery = new MutableLiveData<>();
        galleryImages = new MutableLiveData<>();
    }

    public LiveData<List<Image>> getGalleryImages(Context context) {
            if (galleryImages == null) {
                galleryImages = new MutableLiveData<List<Image>>();
            }
            galleryImages.setValue(gameAppRepository.getGallery(context));
            return galleryImages;
    }

    public void addImageToGallery(Image image, Context context) {
        List<Image> tempList = getGalleryImages(context).getValue();
        tempList.add(image);
        setGalleryImages(tempList);
    }

    public void setGalleryImages(List<Image> images) {
        this.galleryImages.setValue(images);
    }
}
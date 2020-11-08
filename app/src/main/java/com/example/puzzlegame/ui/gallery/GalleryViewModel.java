package com.example.puzzlegame.ui.gallery;

import android.content.res.AssetManager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.repository.GalleryRepository;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<List<Image>> galleryImages;
    private final GalleryRepository galleryRepository;

    public GalleryViewModel() {
        galleryRepository = GalleryRepository.getGalleryRepository();
        galleryImages = new MutableLiveData<>();
    }

    public void updateGallery(AssetManager assetManager) {
        if (galleryRepository.updateImageList(assetManager, true)) {
            ArrayList<Image> tempImage = new ArrayList<>();
            List<Image> images = (galleryRepository.getImageList());
            for (Image img : images){
                tempImage.add(Utils.createImage(assetManager, img.getImgName()));
            }
            galleryImages.postValue(tempImage);
        }
    }

    public MutableLiveData<List<Image>> getImageListObserver() {
        return galleryImages;
    }

    public void setImageToBackground(Image image) {
        galleryRepository.setCurrentBGBitmap(image);
    }
}
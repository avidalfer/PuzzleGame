package com.example.puzzlegame.repository;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.puzzlegame.model.Image;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GalleryRepository {

    private static GalleryRepository galleryRepository;
    private List<Image> imageList;

    private GalleryRepository() {
        imageList = new ArrayList<>();
    }

    public static GalleryRepository getGalleryRepository() {
        if (galleryRepository == null) {
            galleryRepository = new GalleryRepository();
        }
        return galleryRepository;
    }

    public void addImage(Image image) {
        imageList.add(image);
    }

    public int removeImage(Image image) {
        imageList.remove(image);
        return imageList.size();
    }

    public void setImageList(List<Image> imgList){
        if (imgList == null) {
            imgList = new ArrayList<>();
        }
        this.imageList = imageList;
    }

    public List<Image> getImageList(Context context){

        ArrayList<Image> tempImages = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try {
            final String[] files = assetManager.list("img");
            for (String src : files){
                InputStream is = assetManager.open(src);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                Image img = new Image(src, bitmap, bitmap.getWidth(), bitmap.getHeight());
                tempImages.add(img);
            }
            return tempImages;
        } catch (IOException e){
            return null;
        }
    }

}

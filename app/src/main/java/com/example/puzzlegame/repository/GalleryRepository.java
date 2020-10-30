package com.example.puzzlegame.repository;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.util.Log;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.loader.content.AsyncTaskLoader;

import com.example.puzzlegame.model.Image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class GalleryRepository {

    private static GalleryRepository galleryRepository;
    private List<Image> imageList;
    private Context appContext;

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

    public void setImageList(List<Image> imgList) {
        if (imgList == null) {
            imgList = new ArrayList<>();
        }
        this.imageList = imageList;
    }

    public List<Image> getImageList(final Context context) {

        ArrayList<Image> tempImages = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list("img");
            for (String src : files) {
                    InputStream is = assetManager.open("img/"+src);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Image img = new Image(src, bitmap, bitmap.getWidth(), bitmap.getHeight());
                    tempImages.add(img);
                }
                return tempImages;
        } catch (IOException e){
            Debug.logStack("getAssets", "Error when getting assets", 1);
        }
        return null;
    }
}
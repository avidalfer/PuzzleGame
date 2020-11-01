package com.example.puzzlegame.repository;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;

import androidx.constraintlayout.motion.widget.Debug;
import androidx.loader.content.AsyncTaskLoader;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class GalleryRepository {

    private static GalleryRepository galleryRepository;
    private Gallery gallery;
    private List<Image> imageList;
    private Context appContext;
    private AssetManager assetManager;

    private GalleryRepository() {
        imageList = new ArrayList<>();
        gallery = Gallery.getGallery();
    }

    public static GalleryRepository getGalleryRepository() {
        if (galleryRepository == null) {
            galleryRepository = new GalleryRepository();
        }
        return galleryRepository;
    }

    public void addImage(Image image) {
        gallery.addImage(image);
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

    public  List<Image> getImageList (){
        return imageList;
    }

    public void updateImageList(AssetManager am) {
        this.assetManager = am;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] list = assetManager.list("img");
                    for (String src : list) {
                        if (!gallery.isImageStored(src)) {
                            Image img = createImage(src);
                            addImage(img);
                        }
                    }
                    imageList = gallery.getImageList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Image createImage(String src) {

        int thumbW = 120;
        int thumbH = 120;

        try {
            InputStream is = assetManager.open("img/" + src);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            int scaleFactor = Math.min(photoW / thumbW, photoH / thumbH);

            is.reset();

            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;

            Bitmap b = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);

            Image img = new Image(src, b, photoW, photoH, thumbW, thumbH);
            return img;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
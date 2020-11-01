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

import com.example.puzzlegame.common.Utils;
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
    private Bitmap currentBGBitmap;
    private Image currentImage;

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

    public List<Image> getImageList() {
        return imageList;
    }

    public void updateImageList(AssetManager am) {
        this.assetManager = am;
        try {
            String[] list = assetManager.list("img");
            for (String src : list) {
                if (!gallery.isImageStored(src)) {
                    Image img = Utils.createImage(assetManager, src);
                    addImage(img);
                }
            }
            imageList = gallery.getImageList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentBGBitmap(Image img) {
        currentImage = img;
        int gameW = 683;
        int gameH = 1024;

        String src = img.getImgName();
        int photoW = img.getPhotoWidth();
        int photoH = img.getPhotoHeight();

        Bitmap b = Utils.getScaledBitmap(assetManager, src, photoW, gameW, photoH, gameH);

        currentBGBitmap = b;
    }

    public Bitmap getCurrentBGBitmap() {
        return currentBGBitmap;
    }

    public Image getCurrentImage(){
        return currentImage;
    }
}
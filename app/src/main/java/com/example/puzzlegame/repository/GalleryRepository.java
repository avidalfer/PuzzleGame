package com.example.puzzlegame.repository;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalleryRepository {

    private static GalleryRepository galleryRepository;
    private Gallery gallery;
    private List<Image> imageList;
    private Context appContext;
    private AssetManager assetManager;
    private Bitmap currentBGBitmap;
    private Image currentImage;
    private AppDataBase db;

    private GalleryRepository(Application application) {
        imageList = new ArrayList<>();
        db = Utils.getDB(application);
    }

    public static GalleryRepository initGalleryRepository(Application app) {
        if (galleryRepository == null) {
            galleryRepository = new GalleryRepository(app);
        }
        return galleryRepository;
    }

    public static GalleryRepository getGalleryRepository() {
        return galleryRepository;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    /**
     * Check in assets img folder if there is a new image file. If found an image not stored, update database
     * and store the whole images in cache.
     *
     * @param am
     * @param refreshing
     * @return
     */
    public boolean updateImageList(AssetManager am, boolean refreshing) {
        // if is not the first time checking img folder vs db and not refreshing data -> exit
        if (assetManager != null || !refreshing) {
            return false;
        }
        assetManager = am;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] list = assetManager.list("img");
                    for (String src : list) {
                        Image img = db.galleryDAO().findByName(src);
                        if (img == null) {
                            img = Utils.createImage(assetManager, src);
                            db.galleryDAO().insertImages(img);
                        }
                    }
                    imageList = db.galleryDAO().getAllImages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return true;
    }

    /**
     * get bitmap from image selected
     *
     * @param img clicked image from galleryactivity
     */
    public void setCurrentBGBitmap(Image img) {
        currentImage = img;
        int gameW = 683;
        int gameH = 1024;

        String src = img.getImgName();
        int photoW = img.getPhotoWidth();
        int photoH = img.getPhotoHeight();

        currentBGBitmap = Utils.getScaledBitmap(assetManager, src, photoW, gameW, photoH, gameH);
    }

    public Bitmap getCurrentBGBitmap() {
        return currentBGBitmap;
    }

    public Image getCurrentImage() {
        return currentImage;
    }
}
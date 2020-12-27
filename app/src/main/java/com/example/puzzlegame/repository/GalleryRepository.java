package com.example.puzzlegame.repository;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GalleryRepository {

    private static GalleryRepository galleryRepository;
    private List<Image> imageList;
    private AssetManager assetManager;
    private Bitmap currentBGBitmap;
    private Image currentImage;
    private final AppDataBase db;

    private GalleryRepository(Activity act) {
        imageList = new ArrayList<>();
        db = Utils.getDB(act.getApplication());
    }

    public static GalleryRepository initGalleryRepository(Activity act) {
        if (galleryRepository == null) {
            galleryRepository = new GalleryRepository(act);
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
     */
    public void updateImageList(final Activity act, final boolean refreshing) {
        // if is not the first time checking img folder vs db and not refreshing data -> exit
        if (assetManager != null && !refreshing) {
            return;
        }

        assetManager = act.getAssets();
        try {
            Thread t = new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {

                    String[] list = new String[0];
                    try {
                        list = assetManager.list("img");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (String src : list) {
                        Image img = db.galleryDAO().findByName(src);
                        if (img == null) {
                            try {
                                InputStream is = assetManager.open("img/" + src);
                                img = Utils.createImage(is, Uri.parse(src));
                                db.galleryDAO().insertImages(img);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    RefreshAssetImageList(act);
                }
            });
            t.start();
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void RefreshAssetImageList(final Activity act) {
        try {
            int thumbW = 120;
            int thumbH = 120;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    imageList = db.galleryDAO().getAllImages();
                }
            });
            t.start();
            t.join();

            if (imageList == null) {
                return;
            }

            InputStream is = null;
            for (Image im : imageList) {
                if (im.getBitmap() == null) {

                    try {
                        String state = Environment.getExternalStorageState();
                        if (Environment.MEDIA_MOUNTED.equals(state)) {
                            if (Utils.hasReadPermission(act)) {
                                is = act.getContentResolver().openInputStream(Uri.parse(im.getImgName()));
                            }
                        }
                    } catch (Exception ex) {
                        try {
                            is = act.getAssets().open("img/" + im.getImgName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        is = act.getAssets().open("img/" + im.getImgName());
                    } catch (Exception e) {
                        Log.d(TAG, "RefreshAssetImageList: " + e.getMessage());
                    }
                }
                im.setBitmap(Utils.getScaledBitmap(is, im.getPhotoWidth(), thumbW, im.getPhotoHeight(), thumbH));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * get bitmap from image selected
     *
     * @param img clicked image from galleryactivity
     */
    public void setCurrentBGBitmap(Activity act, Image img) {
        currentImage = img;
        int gameW = 683;
        int gameH = 1024;
        int photoW = img.getPhotoWidth();
        int photoH = img.getPhotoHeight();

        InputStream is = null;
        try {
            is = act.getContentResolver().openInputStream(Uri.parse(img.getImgName()));
        } catch (Exception ex) {
            try {
                is = act.getAssets().open("img/" + img.getImgName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        currentBGBitmap = Utils.getScaledBitmap(is, photoW, gameW, photoH, gameH);
    }

    public Bitmap getCurrentBGBitmap() {
        return currentBGBitmap;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public Image addImageToList(final Activity act, final Uri uri) {
        final Image[] _img = new Image[1];
        final int[] id = new int[1];
        int thumbW = 120;
        int thumbH = 120;
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    _img[0] = db.galleryDAO().findByName(uri.toString());
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            InputStream is = act.getContentResolver().openInputStream(uri);
            if (_img[0] == null) {
                _img[0] = Utils.createImage(is, uri);
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        id[0] = (int) db.galleryDAO().insertImages(_img[0]);
                    }
                });
                t1.start();
                t1.join();
            }
            Image tempImg = _img[0];
            if (tempImg.getBitmap() == null) {
                tempImg.setBitmap(Utils.getScaledBitmap(is, tempImg.getPhotoWidth(), thumbW, tempImg.getPhotoHeight(), thumbH));
                imageList.add(tempImg);
                tempImg.imgId = id[0];
                if (currentImage == null) {
                    currentImage = tempImg;
                } else {
                    currentImage.imgId = id[0];
                }
                return tempImg;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
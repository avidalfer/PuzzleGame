package com.example.puzzlegame.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Image implements Serializable {

    private String imgName;
    private Bitmap bitmap;
    private int photoWidth;
    private int photoHeight;

    public Image(String imgName, Bitmap thumbBitmap, int photoWidth, int photoHeight) {
        this.imgName = imgName;
        this.bitmap = thumbBitmap;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
    }

    public Image(String src) {
        imgName = src;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(int photoWidth) {
        this.photoWidth = photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(int photoHeight) {
        this.photoHeight = photoHeight;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

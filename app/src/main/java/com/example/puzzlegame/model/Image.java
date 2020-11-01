package com.example.puzzlegame.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Image implements Serializable {

    private String imgName;
    private Bitmap bitmap;
    private int photoWidth;
    private int photoHeight;
    private int thumbWidth;
    private int thumbHeight;

    public Image(String imgName, Bitmap bitmap, int photoWidth, int photoHeight, int thumbWidth, int thumbHeight) {
        this.imgName = imgName;
        this.bitmap = bitmap;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
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

    public int getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}

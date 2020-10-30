package com.example.puzzlegame.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Image implements Serializable {

    private String imgName;
    private Bitmap bitmap;
    private int width;
    private int heigth;

    public Image(String imgName, Bitmap bitmap, int width, int height) {
        this.imgName = imgName;
        this.bitmap = bitmap;
        this.width = width;
        this.heigth = height;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
}

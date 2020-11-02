package com.example.puzzlegame.model;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;
@Entity
public class Image implements Serializable {

    @ColumnInfo(name = "imgName")
    private String imgName;
    @ColumnInfo(name = "bitmap")
    private Bitmap bitmap;
    @ColumnInfo(name = "photoWidth")
    private int photoWidth;
    @ColumnInfo(name = "phonoHeight")
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

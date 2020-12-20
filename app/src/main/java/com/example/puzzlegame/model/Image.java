package com.example.puzzlegame.model;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "images")
public class Image implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int imgId;
    public String imgName;
    public int photoWidth;
    public int photoHeight;
    @Ignore
    public Bitmap bitmap;

    public Image(){}

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

    public void setImageURI(Uri path) {
    }
}

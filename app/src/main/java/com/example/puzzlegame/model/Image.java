package com.example.puzzlegame.model;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Fts4
@Entity(tableName = "images")
public class Image implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    public int id;
    public String imgName;
    public int photoWidth;
    public int photoHeight;
    @Ignore
    public Bitmap bitmap;

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

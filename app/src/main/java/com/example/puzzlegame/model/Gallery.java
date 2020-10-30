package com.example.puzzlegame.model;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    private static Gallery gallery;
    private List<Image> imageList;

    private Gallery() {
        imageList = new ArrayList<>();
    }

    public static Gallery getGallery() {
        if (gallery == null) {
            gallery = new Gallery();
        }
        return gallery;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void addImage(Image img) {
        this.imageList.add(img);
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public int removeImage(Image img) {
        this.imageList.remove(img);
        return imageList.size();
    }
}

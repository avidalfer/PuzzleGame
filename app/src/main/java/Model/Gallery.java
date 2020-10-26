package Model;

import java.util.List;

public class Gallery {
    private List<Image> imageList;

    public Gallery(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}

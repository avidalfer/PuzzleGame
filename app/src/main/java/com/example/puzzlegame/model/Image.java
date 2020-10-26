package com.example.puzzlegame.model;

public class Image {

    private String source;
    private String imgName;
    private Double size;
    private Double length;

    public Image(String source, String imgName, Double size, Double length) {
        this.source = source;
        this.imgName = imgName;
        this.size = size;
        this.length = length;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}

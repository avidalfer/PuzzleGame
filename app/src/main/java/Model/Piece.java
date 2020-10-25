package Model;

import android.graphics.Bitmap;
import android.media.Image;

public class Piece {
    private GameSession session;
    private Bitmap croppedImage;
    private int length;
    private int width;
    private short left;
    private short top;
    private Image parentImage;

    public Piece(GameSession session, Bitmap croppedImage, int length, int width, short left, short top, Image parentImage) {
        this.session = session;
        this.croppedImage = croppedImage;
        this.length = length;
        this.width = width;
        this.left = left;
        this.top = top;
        this.parentImage = parentImage;
    }

    public GameSession getSession() {
        return session;
    }

    public void setSession(GameSession session) {
        this.session = session;
    }

    public Bitmap getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(Bitmap croppedImage) {
        this.croppedImage = croppedImage;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(short left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(short top) {
        this.top = top;
    }

    public Image getParentImage() {
        return parentImage;
    }

    public void setParentImage(Image parentImage) {
        this.parentImage = parentImage;
    }

}

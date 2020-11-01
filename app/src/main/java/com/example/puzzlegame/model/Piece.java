package com.example.puzzlegame.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

public class Piece extends androidx.appcompat.widget.AppCompatImageView {
    private int xCoord;
    private int yCoord;
    private int pieceWidth;
    private int pieceHeight;
    private boolean canMove = true;

    public Piece(Context context) {
        super(context);
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getPieceWidth() {
        return pieceWidth;
    }

    public void setPieceWidth(int pieceWidth) {
        this.pieceWidth = pieceWidth;
    }

    public int getPieceHeight() {
        return pieceHeight;
    }

    public void setPieceHeight(int pieceHeight) {
        this.pieceHeight = pieceHeight;
    }

    public boolean canMove() {
        return canMove;
    }

    public void canMove(boolean canMove) {
        this.canMove = canMove;
    }
}

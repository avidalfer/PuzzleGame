package com.example.puzzlegame.model;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Piece extends androidx.appcompat.widget.AppCompatImageView {
    @ColumnInfo (name = "xCoord")
    private int xCoord;
    @ColumnInfo(name = "yCoord")
    private int yCoord;
    @ColumnInfo(name = "pieceWidth")
    private int pieceWidth;
    @ColumnInfo(name = "pieceHeight")
    private int pieceHeight;
    @ColumnInfo(name = "canMove")
    private boolean canMove = true;

    public Piece(Context context) {
        super(context);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
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

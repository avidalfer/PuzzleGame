package com.example.puzzlegame.model;

import android.content.Context;

public class Piece extends androidx.appcompat.widget.AppCompatImageView {

    public int id;
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;

    public Piece(Context context) {
        super(context);
    }

    public Piece(int id, int xCoord, int yCoord, int pieceHeight, int pieceWidth, boolean canMove, Context context){
        super (context);}
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

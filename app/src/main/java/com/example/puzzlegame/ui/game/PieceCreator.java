package com.example.puzzlegame.ui.game;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class PieceCreator {
    private static PieceCreator pieceCreator;
    private ArrayList<Piece> pieces;
    private static final String TAG = "PieceCreator";

    private PieceCreator(){
        pieces = new ArrayList<>();
    }

    public static PieceCreator getPieceCreator(){
        if (pieceCreator == null) {
            pieceCreator = new PieceCreator();
        }
        return pieceCreator;
    }

    public ArrayList<Piece> getPieces(ImageView imageView, Level level) {
            int cols = level.getNumCols();
            int rows = level.getNumRows();
            int piecesNumber = cols * rows;

            ArrayList<Piece> pieces = new ArrayList<>(piecesNumber);
            BitmapDrawable bmDrawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = bmDrawable.getBitmap();

            int[] dimensions = getBitmapPositionInsideImageView(imageView);
            int scaledBitmapLeft = dimensions[0];
            int scaledBitmapTop = dimensions[1];
            int scaledBitmapWidth = dimensions[2];
            int scaledBitmapHeight = dimensions[3];

            int croppedImageWidth = scaledBitmapWidth - 2 * abs(scaledBitmapLeft);
            int croppedImageHeight = scaledBitmapHeight - 2 * abs(scaledBitmapTop);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledBitmapWidth, scaledBitmapHeight, true);
            Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, abs(scaledBitmapLeft), abs(scaledBitmapTop), croppedImageWidth, croppedImageHeight);

            int pieceWidth = croppedImageWidth / cols;
            int pieceHeight = croppedImageHeight / rows;

            int yCoord = 0;
            for (int row = 0; row < rows; row++) {
                int xCoord = 0;
                for (int col = 0; col < cols; col++) {
                    int offsetX = 0;
                    int offsetY = 0;
                    if (col > 0) {
                        offsetX = pieceWidth / 3;
                    }
                    if (row > 0) {
                        offsetY = pieceHeight / 3;
                    }
                    int pieceLeft = xCoord - offsetX;
                    int pieceTop = yCoord - offsetY;
                    //apply offset to each piece
                    Bitmap pieceBitmap = Bitmap.createBitmap(croppedBitmap, pieceLeft, pieceTop, pieceWidth + offsetX, pieceHeight + offsetY);
                    Piece piece = new Piece(imageView.getContext());
                    piece.setImageBitmap(pieceBitmap);
                    piece.setxCoord(pieceLeft + imageView.getLeft());
                    piece.setyCoord(pieceTop + imageView.getTop());
                    piece.setPieceWidth(pieceWidth + offsetX);
                    piece.setPieceHeight(pieceHeight + offsetY);

                    Bitmap puzzlePiece = Bitmap.createBitmap(pieceWidth + offsetX, pieceHeight + offsetY, Bitmap.Config.ARGB_8888);

                    int bumpSize = pieceHeight / 4;
                    Canvas canvas = new Canvas(puzzlePiece);
                    Path path = new Path();
                    path.moveTo(offsetX, offsetY);

                    if (row == 0) {
                        // top side piece
                        path.lineTo(pieceBitmap.getWidth(), offsetY);
                    } else {
                        // top bump
                        path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3, offsetY);
                        path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, offsetY);
                        path.lineTo(pieceBitmap.getWidth(), offsetY);
                    }

                    if (col == cols - 1) {
                        // right side piece
                        path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                    } else {
                        // right bump
                        path.lineTo(pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                        path.cubicTo(pieceBitmap.getWidth() - bumpSize,offsetY + (pieceBitmap.getHeight() - offsetY) / 6, pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                        path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                    }

                    if (row == rows - 1) {
                        // bottom side piece
                        path.lineTo(offsetX, pieceBitmap.getHeight());
                    } else {
                        // bottom bump
                        path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, pieceBitmap.getHeight());
                        path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5,pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3, pieceBitmap.getHeight());
                        path.lineTo(offsetX, pieceBitmap.getHeight());
                    }

                    if (col == 0) {
                        // left side piece
                        path.close();
                    } else {
                        // left bump
                        path.lineTo(offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                        path.cubicTo(offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6, offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                        path.close();
                    }

                    // mask the piece
                    Paint paint = new Paint();
                    paint.setColor(0XFF000000);
                    paint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(path, paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    canvas.drawBitmap(pieceBitmap, 0, 0, paint);

                    // draw a white border
                    Paint border = new Paint();
                    border.setColor(0X80FFFFFF);
                    border.setStyle(Paint.Style.STROKE);
                    border.setStrokeWidth(8.0f);
                    canvas.drawPath(path, border);

                    // draw a black border
                    border = new Paint();
                    border.setColor(0X80000000);
                    border.setStyle(Paint.Style.STROKE);
                    border.setStrokeWidth(3.0f);
                    canvas.drawPath(path, border);

                    // set the resulting bitmap to the piece
                    piece.setImageBitmap(puzzlePiece);

                    pieces.add(piece);
                    xCoord += pieceWidth;
                }
                yCoord += pieceHeight;
            }

            return pieces;
        }

    private int[] getBitmapPositionInsideImageView(ImageView imageView) {
        int[] ret = new int[4];

        if (imageView == null || imageView.getDrawable() == null)
            return ret;

        // Get image dimensions
        // Get image matrix values and place them in an array
        float[] f = new float[9];
        imageView.getImageMatrix().getValues(f);

// Extract the scale values using the constants (if aspect ratio maintained, scaleX == scaleY)
        final float scaleX = f[Matrix.MSCALE_X];
        final float scaleY = f[Matrix.MSCALE_Y];

// Get the drawable (could also get the bitmap behind the drawable and getWidth/getHeight)
        final Drawable d = imageView.getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

// Calculate the actual dimensions
        final int actW = Math.round(origW * scaleX);
        final int actH = Math.round(origH * scaleY);

        ret[2] = actW;
        ret[3] = actH;

        // Get image position
        // We assume that the image is centered into ImageView
        int imgViewW = imageView.getWidth();
        int imgViewH = imageView.getHeight();

        int top = (int) (imgViewH - actH) / 2;
        int left = (int) (imgViewW - actW) / 2;

        ret[0] = left;
        ret[1] = top;

        return ret;
    }
}

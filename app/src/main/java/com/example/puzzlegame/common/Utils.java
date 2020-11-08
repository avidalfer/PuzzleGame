package com.example.puzzlegame.common;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.model.PieceData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains static functions for common activities behaviour (i.e. create a common toolbar)
 */
public class Utils {

    public static AppDataBase getDB(Application application) {
        return AppDataBase.getAppDataBase(application);
    }

    public static void TODO(AppCompatActivity activity, View view) {
        Toast toast = Toast.makeText(activity.getApplicationContext(), "Function not implemented yet", Toast.LENGTH_SHORT);
        toast.show();
    }

    public static String FormatTime(long winTime) {
        String formatedTime = "";

        long mil = 1;
        long sec = 1000 * mil;
        long min = 60 * sec;
        long hr = 60 * min;
        long result = Math.abs(winTime);

        int hour = (int) (result / hr);
        result %= hr;
        int minutes = (int) (result / min);
        result %= min;
        int seconds = (int) (result / sec);
        result %= sec;

        if (winTime < 0) {
            formatedTime = "- ";
        }
        if (hour > 0) {
            formatedTime += hour + " h ";
        }
        if (minutes > 0) {
            formatedTime += minutes + " m ";
        }
        if (seconds > 0) {
            formatedTime += seconds + " s ";
        }

        return formatedTime;
    }

    public static Image createImage(AssetManager assetManager, String src) {

        int thumbW = 120;
        int thumbH = 120;

        try {
            InputStream is = assetManager.open("img/" + src);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            is.reset();

            Bitmap b = getScaledBitmap(assetManager, src, photoW, thumbW, photoH, thumbH);

            return new Image(src, b, photoW, photoH);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getScaledBitmap(AssetManager assetManager, String src, int originalW, int targetW, int originalH, int targetH) {

        InputStream is = null;
        try {
            is = assetManager.open("img/" + src);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();

            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = Math.min(originalW / targetW, originalH / targetH);

            Bitmap b = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);

            return b;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PieceData> piecesToData(List<Piece> piecesList) {
        List<PieceData> newListData = new ArrayList<>();
        for (Piece piece : piecesList) {
            PieceData pd = toPieceData(piece);
            newListData.add(pd);
        }
        return newListData;
    }

    public static List<Piece> dataToPieces(List<PieceData> pieceDataList) {
        List<Piece> newListPieces = new ArrayList<>();
        for (PieceData Data : pieceDataList) {
            Piece p = toPiece(Data);
            newListPieces.add(p);
        }
        return newListPieces;
    }

    private static Piece toPiece(PieceData data) {
        return new Piece(
                data.pieceId,
                data.xCoord,
                data.yCoord,
                data.pieceHeight,
                data.pieceWidth,
                data.canMove,
                data.getContext());
    }

    private static PieceData toPieceData(Piece piece) {
        return new PieceData(
                piece.id,
                piece.xCoord,
                piece.yCoord,
                piece.pieceHeight,
                piece.pieceWidth,
                piece.canMove,
                piece.getContext());
    }
}

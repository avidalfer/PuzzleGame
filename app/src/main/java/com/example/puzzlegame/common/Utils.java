package com.example.puzzlegame.common;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.model.PieceData;
import com.example.puzzlegame.ui.gallery.GalleryActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains static functions for common activities behaviour (i.e. create a common toolbar)
 */
public class Utils {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static final int REQUEST_OPEN_DOC_EXTERNAL_STORAGE = 3;
    private static final int REQUEST_CAPTURE_IMAGE = 4;

    public static AppDataBase getDB(Application application) {
        return AppDataBase.getAppDataBase(application);
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

    public static Image createImage(InputStream is, Uri uri) {

        int thumbW = 120;
        int thumbH = 120;

        try {
            String src = uri.toString();
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            Bitmap b = getScaledBitmap(is, photoW, thumbW, photoH, thumbH);

            return new Image(src, b, photoW, photoH);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getScaledBitmap(InputStream is, int originalW, int targetW, int originalH, int targetH) {
        try {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();

            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = Math.min(originalW / targetW, originalH / targetH);

            Bitmap b = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);

            return b;
        } catch (Exception e) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasWritePermission(Activity act) {
        act.onRequestPermissionsResult(REQUEST_WRITE_EXTERNAL_STORAGE, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new int[]{PackageManager.PERMISSION_GRANTED});
        if (ContextCompat.checkSelfPermission(act, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean hasReadPermission(Activity act) {
        act.onRequestPermissionsResult(REQUEST_READ_EXTERNAL_STORAGE, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new int[]{PackageManager.PERMISSION_GRANTED});
        if (ContextCompat.checkSelfPermission(act, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            return true;
        }
        return false;
    }

    public static boolean hasCameraPermission(GalleryActivity act) {
        act.onRequestPermissionsResult(REQUEST_CAPTURE_IMAGE, new String[]{Manifest.permission.CAMERA}, new int[]{PackageManager.PERMISSION_GRANTED});
        if (ContextCompat.checkSelfPermission(act, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.CAMERA);
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.CAMERA}, REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            return true;
        }
        return false;
    }
}

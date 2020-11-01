package com.example.puzzlegame.ui.game;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameSessionRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class PuzzleGameViewModel extends ViewModel {
    private final GameSessionRepository gameSessionRepository;
    private Level level;
    private GalleryRepository galleryRepository;
    private MutableLiveData<List<Piece>> pieces;
    private MutableLiveData<Image> boardImage;
    private long playedTime;
    private static final String TAG = "PuzzleGameViewModel";

    public PuzzleGameViewModel() {

        galleryRepository = GalleryRepository.getGalleryRepository();
        gameSessionRepository = GameSessionRepository.getGameSessionRepository();
        pieces = new MutableLiveData<>();
        boardImage = new MutableLiveData<>();
    }

    public void setBackgroundBitmap() {
        Image boardImage = galleryRepository.getCurrentImage();
        this.boardImage.postValue(boardImage);
    }

    public MutableLiveData<List<Piece>> getPiecesObservable() {
        return pieces;
    }

    public MutableLiveData<Image> getBoardImageObservable() {
        return boardImage;
    }

    public void saveGameStatus() {
        if (pieces.getValue() == null) {
            pieces.setValue(new ArrayList<Piece>());
        }
        gameSessionRepository.saveGameSession(pieces.getValue(), playedTime, level);
    }

    public Bitmap getCurrentBitmap() {
        return galleryRepository.getCurrentBGBitmap();
    }

    public void createPieces(ImageView imageView, Level level) {
        PieceCreator pieceCreator = PieceCreator.getPieceCreator();
        pieces.postValue(pieceCreator.getPieces(imageView, level));
        saveGameStatus();
    }

    public boolean checkGameOver() {
        if (pieces.getValue() == null) {
            pieces.setValue(new ArrayList<Piece>());
        }
        for (Piece piece : pieces.getValue()) {
            if (piece.canMove()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Piece> getpieces() {
        return (ArrayList<Piece>) pieces.getValue();
    }

    public void placedPiece(Piece piece) {
        gameSessionRepository.updateGameSate(piece);
    }

    public long getCurrentPlayedTime() {
        if (playedTime == 0) {
            playedTime = SystemClock.elapsedRealtime();
        }
        return playedTime;
    }

    public void setCurrentTime(long playedTime) {
        this.playedTime = playedTime;
    }
}

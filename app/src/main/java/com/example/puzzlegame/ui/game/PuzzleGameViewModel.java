package com.example.puzzlegame.ui.game;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameSessionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleGameViewModel extends ViewModel {
    private final GameSessionRepository gameSessionRepository;
    private Level level;
    private GalleryRepository galleryRepository;
    private MutableLiveData<List<Piece>> placedPieces;
    private MutableLiveData<Image> boardImage;
    private long playedTime;

    public PuzzleGameViewModel() {

        galleryRepository = GalleryRepository.getGalleryRepository();
        gameSessionRepository = GameSessionRepository.getGameSessionRepository();
        placedPieces = new MutableLiveData<>();
        boardImage = new MutableLiveData<>();
    }

    public void setBackgroundBitmap() {
        Image boardImage = galleryRepository.getCurrentImage();
        this.boardImage.postValue(boardImage);
    }

    public MutableLiveData<List<Piece>> getPiecesObservable() {
        return placedPieces;
    }

    public MutableLiveData<Image> getBoardImageObservable() {
        return boardImage;
    }

    public void saveGameStatus() {
        if (placedPieces.getValue() == null) {
            placedPieces.setValue(new ArrayList<Piece>());
        }
        gameSessionRepository.saveGameSession(placedPieces.getValue(), playedTime, level);
    }

    public Bitmap getCurrentBitmap() {
        return galleryRepository.getCurrentBGBitmap();
    }

    public void createPieces(ImageView imageView, Level level) {
        PieceCreator pieceCreator = PieceCreator.getPieceCreator();
        ArrayList<Piece> gotPieces = pieceCreator.getPieces(imageView, level);
        Collections.shuffle(gotPieces);
        placedPieces.postValue(gotPieces);
        saveGameStatus();
    }

    public boolean checkGameOver() {
        if (placedPieces.getValue() == null) {
            placedPieces.setValue(new ArrayList<Piece>());
        }
        for (Piece piece : placedPieces.getValue()) {
            if (piece.canMove()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Piece> getpieces() {
        return (ArrayList<Piece>) placedPieces.getValue();
    }

    public void placedPiece(Piece piece) {
        gameSessionRepository.updateGameSate(piece);
    }

    public long getCurrentPlayedTime() {
        return playedTime;
    }

    public void setCurrentTime(long playedTime) {
        this.playedTime = playedTime;
    }
}

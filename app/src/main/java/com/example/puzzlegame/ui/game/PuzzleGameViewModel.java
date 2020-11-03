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
    private MutableLiveData<List<Piece>> totalPieces;
    private MutableLiveData<Image> boardImage;
    private MutableLiveData<Boolean> gameOver;
    private long playedTime;
    private ArrayList<Piece> placedPieces;

    public PuzzleGameViewModel() {

        galleryRepository = GalleryRepository.getGalleryRepository();
        gameSessionRepository = GameSessionRepository.getGameSessionRepository();
        totalPieces = new MutableLiveData<>();
        placedPieces = new ArrayList<>();
        boardImage = new MutableLiveData<>();
        gameOver = new MutableLiveData<>();
    }

    public void setBackgroundBitmap() {
        Image boardImage = galleryRepository.getCurrentImage();
        this.boardImage.postValue(boardImage);
    }

    public MutableLiveData<List<Piece>> getPiecesObservable() {
        return totalPieces;
    }

    public MutableLiveData<Image> getBoardImageObservable() {
        return boardImage;
    }

    public void saveGameStatus() {
        if (totalPieces.getValue() == null) {
            totalPieces.setValue(new ArrayList<Piece>());
            gameSessionRepository.saveGameSession(totalPieces.getValue(), playedTime, level);
        }
        gameSessionRepository.updateGameSate(playedTime);
    }

    public Bitmap getCurrentBitmap() {
        return galleryRepository.getCurrentBGBitmap();
    }

    public void createPieces(ImageView imageView, Level level) {
        PieceCreator pieceCreator = PieceCreator.getPieceCreator();
        ArrayList<Piece> gotPieces = pieceCreator.getPieces(imageView, level);
        Collections.shuffle(gotPieces);
        totalPieces.postValue(gotPieces);
        saveGameStatus();
    }

    public boolean checkGameOver() {
        if (totalPieces.getValue() == null) {
            totalPieces.setValue(new ArrayList<Piece>());
        }
        for (Piece piece : totalPieces.getValue()) {
            if (piece.canMove()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Piece> getpieces() {
        return (ArrayList<Piece>) totalPieces.getValue();
    }

    public void placedPiece(Piece piece) {
        gameSessionRepository.updateGameSate(piece);
        placedPieces.add(piece);
        if (placedPieces.size() == totalPieces.getValue().size()){
            gameOver.setValue(true);
        }
    }

    public MutableLiveData<Boolean> getGameOverObservable () {
        return gameOver;
    }

    public long getCurrentPlayedTime() {
        return playedTime;
    }

    public void setCurrentTime(long playedTime) {
        this.playedTime = playedTime;
    }

    public void updateGameStatus() {
        gameSessionRepository.updateGameSate(playedTime);
    }
}

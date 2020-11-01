package com.example.puzzlegame.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.model.User;

import java.util.List;

public class GameSessionRepository {
    private static GameSessionRepository gameSessionRepository;
    private GameAppRepository gameAppRepository;
    private GalleryRepository galleryRepository;
    private GameSession gameSession;
    private User user;

    private GameSessionRepository (){
        gameAppRepository = GameAppRepository.getGameAppRepository();
        galleryRepository = GalleryRepository.getGalleryRepository();
        user = gameAppRepository.getCurrentUser();
        gameSession = user.getCurrentGameSession();
    }

    public static GameSessionRepository getGameSessionRepository() {
        if (gameSessionRepository == null) {
            gameSessionRepository = new GameSessionRepository();
        }
        return gameSessionRepository;
    }

    public void saveGameSession(List<Piece> pieces, long playedTime, Level level) {
        if (user.getCurrentGameSession() == null){
            GameSession currentGame = new GameSession(user, level);
            currentGame.setBgImage(galleryRepository.getCurrentImage());
            user.setCurrentGameSession(currentGame);
        }
        user.getCurrentGameSession().setPlacedPieces(pieces);
        user.getCurrentGameSession().setEndTime(playedTime);
    }

    public void updateGameSate(Piece piece) {
        user.getCurrentGameSession().addPlacedPiece(piece);
    }
}

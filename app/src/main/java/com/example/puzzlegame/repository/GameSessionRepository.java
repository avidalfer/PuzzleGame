package com.example.puzzlegame.repository;

import android.app.Application;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
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
    private AppDataBase db;
    private User user;

    private GameSessionRepository() {
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

    /**
     * Set the current session as the user current game session to get opened when user continues a game
     * @param pieces
     * @param playedTime
     * @param level
     */
    public void saveGameSession(List<Piece> pieces, long playedTime, Level level, Application app) {
        db = Utils.getDB(app);
        GameSession currentGame = new GameSession(user, level);
        currentGame.setBgImage(galleryRepository.getCurrentImage());

        user.setCurrentGameSession(currentGame);
        user.getCurrentGameSession().setPieces(pieces);
        user.getCurrentGameSession().setEndTime(playedTime);
    }

    /**
     * Update pieces location and current last played time.
     * @param pieces
     * @param playedTime
     */
    public void updateGameSession(List<Piece> pieces, long playedTime) {
        user.getCurrentGameSession().setPieces(pieces);
        user.getCurrentGameSession().setEndTime(playedTime);
    }
}

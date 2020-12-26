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
    private final GameAppRepository gameAppRepository;
    private final GalleryRepository galleryRepository;
    private GameSession gameSession;
    private AppDataBase db;
    private final User user;

    private GameSessionRepository() {
        gameAppRepository = GameAppRepository.getGameAppRepository();
        galleryRepository = GalleryRepository.getGalleryRepository();
        // en previsión de recuperar partida
        user = gameAppRepository.getCurrentUser();
        gameSession = user.getCurrentGameSession();
        //
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
     * @return
     */
    public GameSession saveGameSession(final List<Piece> pieces, final long playedTime, Level level, Application app) {
        db = Utils.getDB(app);
        if (gameSession == null) {
            gameSession = new GameSession(level);
            gameSession.setUser(user);
            gameSession.setPieces(pieces);
            gameSession.setBgImage(galleryRepository.getCurrentImage());
        }
            updateGameSession(playedTime);
        return gameSession;
    }

    /**
     * Update pieces location and current last played time.
     * @param playedTime
     */
    public void updateGameSession(final long playedTime) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameSession.pieceDataList = Utils.piecesToData(gameSession.getPieces());
                gameSession.setEndTime(playedTime);
                long gsId = db.gameSessionDAO().insertGameSession(gameSession);
                gameSession.setId(gsId);
                user.setCurrentGameSession(gameSession);
            }
        }).start();
    }

    public void gameOver(){
        increaseUserLvl();
    }

    private void increaseUserLvl() {
        List<Level> levels = gameAppRepository.getLevels();
        int userLvlid = user.getUserLvl().getId();
        if (gameSession.gameLvlId == userLvlid) {
            if (userLvlid < (levels.size())) {
                {
                    gameAppRepository.setUserLevel(userLvlid+1);
                }
            }
        }
    }

    private void deleteCurrentGame() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.gameSessionDAO().deleteGame(gameSession);
            }
        }).start();
    }
}

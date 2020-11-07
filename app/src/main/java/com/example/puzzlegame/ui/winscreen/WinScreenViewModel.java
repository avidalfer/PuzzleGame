package com.example.puzzlegame.ui.winscreen;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.User;

public class WinScreenViewModel extends ViewModel {

    private GameSession session;
    private int sessionId;
    private User user;

    public WinScreenViewModel() {
        //HallOfFame localHoF = GameApp.getLocalHof();
        user = new User(1, "Pepe", Language.ES);
        //user se saca del login.
        //User user = GameApp.getUser(id);
        //sessionId se pasa por intent desde la activity de juego
        //session = user.getCurrentSession(sessionId);
    }

    public Long getWinnerTime() {
        //localHof.getSession().getResolvedTime();
        return 70000L;
    }

    public void setWinnerName(String winnerName) {
        //localHoF.getSession().setWinnerName(winnerName);
    }

    public String getFormattedTime() {
        long winTime = getWinnerTime();
        return Utils.FormatTime(winTime);
    }

    public String getUserName() {
        if (user != null) {
            return user.getName();
        }
        return "";
    }

    public GameSession getGameSessionById(long gameSessionId) {
        //find in repository gameSession by id
        return new GameSession();
    }

    public long addNewScore(String winnerName, GameSession gameSession) {
        //call LocalHallOfFame repository to create new score with this params.
        return 0;
    }
}

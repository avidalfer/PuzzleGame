package com.example.puzzlegame.ui.winscreen;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.HallOfFame;
import com.example.puzzlegame.model.User;

public class WinScreenViewModel extends ViewModel {

    private GameSession session;
    private int sessionId;
    private User user;

    public WinScreenViewModel() {
        HallOfFame localHoF = HallOfFame.getHof();
        //user se saca del login.
        //User user = GameApp.getUser(id);
        //sessionId se pasa por intent desde la activity de juego
        //session = user.getCurrentSession(sessionId);

    }

    public Long getWinTime() {
        //session.getResolvedTime();
        return 70000L;
    }
    public void setWinnerName() {

    }
}

package com.example.puzzlegame.ui.winscreen;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.HallOfFame;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.User;

public class WinScreenViewModel extends ViewModel {

    private GameSession session;
    private int sessionId;
    private User user;

    public WinScreenViewModel() {
        //HallOfFame localHoF = GameApp.getLocalHof();
        user = new User(1L, "Pepe", Language.ES);
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
}

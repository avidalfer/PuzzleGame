package com.example.puzzlegame.ui.winscreen;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.repository.GameAppRepository;
import com.example.puzzlegame.repository.HallOfFameRepository;

import java.util.List;

public class WinScreenViewModel extends ViewModel {

    private GameSession session;
    private int sessionId;
    private User user;
    private HallOfFameRepository hallOfFameRepository;
    private GameAppRepository gameAppRepository;

    public WinScreenViewModel() {
        gameAppRepository = GameAppRepository.getGameAppRepository();
        hallOfFameRepository = HallOfFameRepository.getInstance();
    }

    public String getFormattedTime(long winTime) {
        return Utils.FormatTime(winTime);
    }

    public String getUserName() {
        return gameAppRepository.getCurrentUser().getName();
    }

    public void saveScore(Score score) {
        hallOfFameRepository.saveScore(score);
    }

    public void initRepo(Application application) {
        hallOfFameRepository.initHallOfFameRepository(application);
    }

    public Boolean isRecord(Score score) {
        List<Score> savedScores = hallOfFameRepository.getScores();
        for (Score sc: savedScores) {
            if (sc.winTime > score.winTime) {
                return false;
            }
        }
        return true;
    }
}


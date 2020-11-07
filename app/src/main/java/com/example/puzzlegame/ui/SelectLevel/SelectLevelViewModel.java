package com.example.puzzlegame.ui.SelectLevel;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.repository.GameAppRepository;

public class SelectLevelViewModel extends ViewModel {
    private GameAppRepository gameAppRepository;
    private Level lvl;
    private final User user;

    public SelectLevelViewModel(){
        gameAppRepository = GameAppRepository.getGameAppRepository();
        user = gameAppRepository.getCurrentUser();
        lvl = user.getUserLvl();
    }

    public Level getGameLevel() {
        return lvl;
    }

    public Level getLastPlayedLevel(){
        GameSession lastGameSession = user.getCurrentGameSession();
        if ( lastGameSession == null){
            return user.getUserLvl();
        }
        return lastGameSession.getGameLvl();
    }

    public void setGameLevelById(int lvlId) {
        lvl = gameAppRepository.getLevels().get(lvlId);
    }
}

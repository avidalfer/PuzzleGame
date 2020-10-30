package com.example.puzzlegame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private long idUser;
    private String name;
    private Level userLvl;
    private Language language;
    private GameSession currentGameSession;
    private List<GameSession> playedGames;


    public User(long idUser, String name, Language language) {
        this.idUser = idUser;
        this.name = name;
        setUserLvl(GameApp.getGameApp().getLevelById(1));
        this.language = language;
        this.playedGames = new ArrayList<>();
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(Level userLvl) {
        if (userLvl == null) {
            this.userLvl = new Level (1, "FÁCIL", 3, 4);
        }
        this.userLvl = userLvl;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public GameSession getCurrentGameSession() {
        return currentGameSession;
    }

    public void setCurrentGameSession(GameSession currentGameSession) {
        this.currentGameSession = currentGameSession;
    }

    public List<GameSession> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameSession> playedGames) {
        this.playedGames = playedGames;
    }
}

package com.example.puzzlegame.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long idUser;
    private String name;
    private Level userLvl;
    private Language language;
    private List<GameSession> playedGames;


    public User(long idUser, String name, Language language) {
        this.idUser = idUser;
        this.name = name;
        //this.userLvl = GameApp.getLevelById(1);
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
        this.userLvl = userLvl;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<GameSession> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameSession> playedGames) {
        this.playedGames = playedGames;
    }
}

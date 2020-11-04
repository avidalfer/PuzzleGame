package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private long idUser;

    @ColumnInfo (name = "name")
    private String name;

    @Embedded
    private Level userLvl;

    @Embedded
    private Language language;

    @Embedded
    private GameSession currentGameSession;

//    @ColumnInfo(name = "playedGames")
    private List<GameSession> playedGames;

//    @ColumnInfo(name = "gameApp")
    private GameApp gameApp;


    public User(long idUser, String name, Language language) {
        this.idUser = idUser;
        this.name = name;
        this.gameApp = GameApp.getGameApp();
        setUserLvl(gameApp.getLevelById(1));
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

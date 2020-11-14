package com.example.puzzlegame.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.LanguageConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "users", foreignKeys = {
        @ForeignKey(entity = Level.class,
        parentColumns = "levelId",
        childColumns = "userLvlId",
        onDelete = ForeignKey.RESTRICT,
        onUpdate = ForeignKey.CASCADE),

        @ForeignKey(entity = GameSession.class,
        parentColumns = "gameId",
        childColumns = "currentGameId",
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE)
})
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public Integer userId;
    public String name;
    public int userLvlId;
    @Nullable
    public Long currentGameId;
    @Ignore
    private Level userLvl;
    @TypeConverters(LanguageConverter.class)
    private Language language;
    @Ignore
    private GameSession currentGameSession;
    @Ignore
    public List<GameSession> playedGames;

    public User() {
    }

    public User(int idUser, String name, Language language) {
        this.userId = idUser;
        this.name = name;
        this.userLvl = null;
        this.userLvlId = 0;
        this.language = language;
        this.playedGames = new ArrayList<>();
    }

    public int getIdUser() {
        return userId;
    }

    public void setIdUser(int idUser) {
        this.userId = idUser;
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
        this.userLvlId = userLvl.getId();
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
        currentGameId = currentGameSession.getId();
    }

    public List<GameSession> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameSession> playedGames) {
        this.playedGames = playedGames;
    }
}

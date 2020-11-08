package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.GameSessionConverter;
import com.example.puzzlegame.basededatos.typeconverters.LanguageConverter;
import com.example.puzzlegame.basededatos.typeconverters.LevelConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Fts4
@Entity(tableName = "users")
@TypeConverters({LanguageConverter.class,
        LevelConverter.class,
        GameSessionConverter.class})
public class User implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public Integer id;
    public String name;
    public Level userLvl;
    public Language language;
    public GameSession currentGameSession;
    public List<GameSession> playedGames;

    public User(){}

    public User(int idUser, String name, Language language) {
        this.id = idUser;
        this.name = name;
        this.userLvl = new Level (1, "FÁCIL", 3, 4);
        this.language = language;
        this.playedGames = new ArrayList<>();
    }

    public int getIdUser() {
        return id;
    }

    public void setIdUser(int idUser) {
        this.id = idUser;
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

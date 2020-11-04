package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.puzzlegame.common.Utils;

@Entity
public class Score {

    @PrimaryKey (autoGenerate = true)
    private Long id;

    @ColumnInfo (name = "winnerName")
    private String winnerName;

    @Embedded
    private GameSession gameSession;

    public Score(GameSession gameSession, String name) {
        this.winnerName = name;
        this.gameSession = gameSession;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getTimeToFinish() {
        return gameSession.getEndTime();
    }

    public String getFormattedTimeToFinish() {
        return Utils.FormatTime(getTimeToFinish());
    }

    public String getWinnerName() {
        return winnerName;
    }

    public int getLevelId(){
        return gameSession.getGameLvl().getId();
    }

    public String getLevelName() {
        return gameSession.getGameLvl().getName();
    }
}

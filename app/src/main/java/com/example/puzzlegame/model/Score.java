package com.example.puzzlegame.model;

import com.example.puzzlegame.common.Utils;

public class Score {

    private Long id;
    private String winnerName;
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

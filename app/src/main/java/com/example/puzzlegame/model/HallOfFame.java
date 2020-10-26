package com.example.puzzlegame.model;

import java.sql.Time;
import java.util.List;

public class HallOfFame {

    private short position;

    private List<GameSession> sessions;

    private String winnerName;
    private Long bestTime;

    public HallOfFame() { }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public List<GameSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession> sessions) {
        this.sessions = sessions;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public Long getBestTime() {
        return bestTime;
    }

    public void setBestTime(Long bestTime) {
        this.bestTime = bestTime;
    }
}

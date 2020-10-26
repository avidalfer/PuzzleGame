package com.example.puzzlegame.model;

import java.sql.Time;
import java.util.List;

public class HallOfFame {

    private static HallOfFame hof = new HallOfFame();
    private List<GameSession> sessions;
    private User user;
    private Time bestTime;


    private HallOfFame() {
    }

    public static HallOfFame getHof() {
        return hof;
    }

    public List<GameSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<GameSession> sessions) {
        this.sessions = sessions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getBestTime() {
        return bestTime;
    }

    public void setBestTime(Time bestTime) {
        this.bestTime = bestTime;
    }
}

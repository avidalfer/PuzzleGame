package Model;

import java.sql.Time;
import java.util.List;

public class HallOfFame {
    private List<GameSession> sessions;
    private User user;
    private Time bestTime;


    public HallOfFame(List<GameSession> sessions, User user, Time bestTime) {
        this.sessions = sessions;
        this.user = user;
        this.bestTime = bestTime;
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

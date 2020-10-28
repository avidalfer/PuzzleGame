package com.example.puzzlegame.model;

import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;

public class LocalHallOfFame implements HallOfFame {

    private static final LocalHallOfFame localHallOfFame = new LocalHallOfFame();
    private final ArrayList<Score> localScores = new ArrayList<>();

    public static LocalHallOfFame getLocalHallOfFame() {
        return localHallOfFame;
    }

    public void addScore(Score score) {
        localScores.add(score);
    }

    public Score getScoreAtPosition(int position) {
        return localScores.get(position);
    }

    public Score getScoreByName(String name) {
        for (Score sc : localScores) {
            if (sc.getWinnerName().compareTo(name) == 0) {
                return sc;
            }
        }
        return null;
    }

    public ArrayList<Score> getLocalScores() {
        return localScores;
    }

    private boolean cleanHallOfFame() {
        localScores.clear();
        return (localScores.size() == 0);
    }

    private int removeScore(Score score) {
        localScores.remove(score);
        return localScores.size();
    }
}

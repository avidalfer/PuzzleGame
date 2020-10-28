package com.example.puzzlegame.model;

import java.util.Comparator;

public class TimeScoreSorter implements Comparator<Score> {

    @Override
    public int compare(Score score1, Score score2) {
        return score1.getTimeToFinish().compareTo(score2.getTimeToFinish());
    }
}

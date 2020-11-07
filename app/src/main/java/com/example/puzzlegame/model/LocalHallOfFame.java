package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;
@Fts4
@Entity (tableName = "scores")
public class LocalHallOfFame implements HallOfFame {

    private static final LocalHallOfFame localHallOfFame = new LocalHallOfFame();

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;
    @Ignore
    public final ArrayList<Score> localScores = new ArrayList<>();


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
        return true;
    }

    private int removeScore(Score score) {
        localScores.remove(score);
        return localScores.size();
    }
}

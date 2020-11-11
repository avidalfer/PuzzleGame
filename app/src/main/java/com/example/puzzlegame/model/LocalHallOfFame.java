package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;

@Entity (tableName = "scores")
public class LocalHallOfFame implements HallOfFame {

    private static final LocalHallOfFame localHallOfFame = new LocalHallOfFame();

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lHofId")
    public long id;
    @Ignore
    public final ArrayList<Score> localScores = new ArrayList<>();

    public LocalHallOfFame(){}

    public static LocalHallOfFame getLocalHallOfFame() {
        return localHallOfFame;
    }

    public long getId() {
        return id;
    }

    public void addScore(Score score) {
        localScores.add(score);
    }

    public Score getScoreAtPosition(int position) {
        return localScores.get(position);
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

    @Override
    public void setId(long insertHof) {
        this.id = insertHof;
    }
}

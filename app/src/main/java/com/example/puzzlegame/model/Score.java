package com.example.puzzlegame.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.io.Serializable;

@Entity(tableName = "scores", indices = @Index(value = "winTime"))
public class Score implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String winnerName;
    public long winTime;
    public long hofId;
    @Ignore
    public LocalHallOfFame hallOfFame;

    public Score(){}

    public Score(String winnerName, long winTime) {
        this.winnerName = winnerName;
        this.winTime = winTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public long getWinTime() {
        return winTime;
    }

    public HallOfFame getHallOfFame() {
        return hallOfFame;
    }

    public void setHallOfFame(LocalHallOfFame hof) {
        this.hallOfFame = LocalHallOfFame.getLocalHallOfFame();
        this.hofId = hallOfFame.getId();
    }
}

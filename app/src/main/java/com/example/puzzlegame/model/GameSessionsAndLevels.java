package com.example.puzzlegame.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GameSessionsAndLevels {
    @Embedded
    public Level level;
    @Relation(parentColumn = "id",
    entityColumn = "gameLvl")
    public List<GameSession> getGameSessionsLevel;
}

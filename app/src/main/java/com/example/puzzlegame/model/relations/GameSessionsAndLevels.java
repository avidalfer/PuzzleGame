package com.example.puzzlegame.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Level;

import java.util.List;

public class GameSessionsAndLevels {
    @Embedded
    public Level level;
    @Relation(parentColumn = "levelId",
    entityColumn = "gameLvlId")
    public List<GameSession> getGameSessionsLevel;
}

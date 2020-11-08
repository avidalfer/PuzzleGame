package com.example.puzzlegame.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.PieceData;

import java.util.List;

public class GameSessionWithPieces {
    @Embedded
    public GameSession gameSession;
    @Relation(parentColumn = "gameId",
    entityColumn = "gameSessionId")
    public List<PieceData> pieces;
}

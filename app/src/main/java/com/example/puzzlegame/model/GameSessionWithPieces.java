package com.example.puzzlegame.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GameSessionWithPieces {
    @Embedded
    public GameSession gameSession;
    @Relation(parentColumn = "id",
    entityColumn = "gameSession")
    public List<Piece> pieces;
}

package com.example.puzzlegame.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GameSessionAndPieces {
    @Embedded
    public GameSession gameSession;
    @Relation(parentColumn = "id",
            entityColumn = "gameSession",
            entity = Piece.class)
    public List<Piece> totalPieces;
}

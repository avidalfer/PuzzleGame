package com.example.puzzlegame.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.model.PieceData;

import java.util.List;

public class GameSessionAndPieces {
    @Embedded
    public GameSession gameSession;
    @Relation(parentColumn = "id",
            entityColumn = "gameSession",
            entity = PieceData.class)
    public List<Piece> totalPieces;
}

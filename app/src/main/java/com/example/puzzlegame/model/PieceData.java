package com.example.puzzlegame.model;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pieces",
foreignKeys = @ForeignKey(entity = GameSession.class,
parentColumns = "gameId",
childColumns = "gameSessionId",
onDelete = ForeignKey.CASCADE,
onUpdate = ForeignKey.CASCADE))
public class PieceData{

    @PrimaryKey(autoGenerate = true)
    public Integer pieceId;
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;
    public long gameSessionId;
    @Ignore
    private Context context;

    public PieceData() {}

    @Ignore
    private GameSession gameSession;

    public PieceData(int id, int xCoord, int yCoord, int pieceHeight, int pieceWidth, boolean canMove, Context context) {
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
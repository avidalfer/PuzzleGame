package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "pieces")
public class PieceData{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public Integer id;
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;
}
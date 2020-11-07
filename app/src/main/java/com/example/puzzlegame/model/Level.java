package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Fts4
@Entity(tableName = "levels")
public class Level implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;
    public String name;
    public int numPieces;
    public int numCols;
    public int numRows;

    public Level(){}

    public Level(int id, String name, int cols, int rows) {
        this.id = id;
        this.name = name;
        this.numCols = cols;
        this.numRows = rows;
        this.numPieces = numCols * numRows;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }
}

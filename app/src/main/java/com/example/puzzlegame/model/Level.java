package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Level implements Serializable {
    @ColumnInfo(name = "idLevel")
    private int id;
    @ColumnInfo(name = "nameLevel")
    private String name;
    @ColumnInfo(name = "numPieces")
    private int numPieces;
    @ColumnInfo(name = "numCols")
    private int numCols;
    @ColumnInfo(name = "numRows")
    private int numRows;
    @ColumnInfo(name = "playedGames")
    private List<GameSession> playedGames;


    public Level(int id, String name, int cols, int rows) {
        this.id = id;
        this.name = name;
        this.numCols = cols;
        this.numRows = rows;
        this.numPieces = numCols * numRows;
        this.playedGames = new ArrayList<>();
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

    public List<GameSession> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameSession> playedGames) {
        this.playedGames = playedGames;
    }
}

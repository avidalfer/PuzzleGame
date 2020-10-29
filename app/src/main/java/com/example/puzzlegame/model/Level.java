package com.example.puzzlegame.model;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int id;
    private String name;
    private int numPieces;
    private int numCols;
    private int numRows;
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

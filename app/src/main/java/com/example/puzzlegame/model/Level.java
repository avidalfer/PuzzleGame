package com.example.puzzlegame.model;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int id;
    private String name;
    private int numPieces;
    private List<GameSession> playedGames;


    public Level(int id, String name, int numPieces) {
        this.id = id;
        this.name = name;
        this.numPieces = numPieces;
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

    public List<GameSession> getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(List<GameSession> playedGames) {
        this.playedGames = playedGames;
    }
}

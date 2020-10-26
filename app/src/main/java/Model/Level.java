package Model;

import java.util.List;

public class Level {
private String name;
private int numPieces;
private List<GameSession> playedGames;


    public Level(String name, int numPieces, List<GameSession> playedGames) {
        this.name = name;
        this.numPieces = numPieces;
        this.playedGames = playedGames;
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

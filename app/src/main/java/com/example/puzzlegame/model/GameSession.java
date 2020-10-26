package com.example.puzzlegame.model;

import android.media.Image;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class GameSession {

    public String user;
    public Image bgImage;
    public Level gameLvl;
    public LocalDateTime initTime;
    public LocalDateTime endTime;
    private List<Piece> placedPieces;


    public GameSession(String user, Image bgImage, Level gameLvl, LocalDateTime initTime, LocalDateTime endTime, List<Piece> placedPieces) {
        this.user = user;
        this.bgImage = bgImage;
        this.gameLvl = gameLvl;
        this.initTime = initTime;
        this.endTime = endTime;
        this.placedPieces = placedPieces;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Image getBgImage() {
        return bgImage;
    }

    public void setBgImage(Image bgImage) {
        this.bgImage = bgImage;
    }

    public Level getGameLvl() {
        return gameLvl;
    }

    public void setGameLvl(Level gameLvl) {
        this.gameLvl = gameLvl;
    }

    public LocalDateTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalDateTime initTime) {
        this.initTime = initTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Piece> getPlacedPieces() {
        return placedPieces;
    }

    public void setPlacedPieces(List<Piece> placedPieces) {
        this.placedPieces = placedPieces;
    }
}

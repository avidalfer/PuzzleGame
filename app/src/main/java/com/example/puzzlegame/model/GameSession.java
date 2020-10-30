package com.example.puzzlegame.model;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameSession implements Serializable {

    public Long id;
    public User user;
    public Image bgImage;
    public Level gameLvl;
    public Long endTime;
    private List<Piece> placedPieces;

    public GameSession() {
    }

    public GameSession(User user, Level level) {
        this.user = user;
        this.gameLvl = level;
        this.endTime = 0L;
        this.placedPieces = new ArrayList<>();
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<Piece> getPlacedPieces() {
        return placedPieces;
    }

    public void setPlacedPieces(List<Piece> placedPieces) {
        this.placedPieces = placedPieces;
    }
}

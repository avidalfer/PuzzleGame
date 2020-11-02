package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.puzzlegame.model.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class GameSession implements Serializable {

    @ColumnInfo(name = "id")
    public Long id;
    @ColumnInfo(name = "user")
    public User user;
    @ColumnInfo(name = "bgImage")
    public Image bgImage;
    @ColumnInfo(name = "gameLvl")
    public Level gameLvl;
    @ColumnInfo(name = "endTime")
    public Long endTime;
    @ColumnInfo(name = "placedPieces")
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

    public void addPlacedPiece(Piece piece) {
        this.placedPieces.add(piece);
    }

    public List<Piece> getPlacedPieces() {
        return placedPieces;
    }

    public void setPlacedPieces(List<Piece> placedPieces) {
        this.placedPieces = placedPieces;
    }
}

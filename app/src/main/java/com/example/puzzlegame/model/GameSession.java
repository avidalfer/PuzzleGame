package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.ImageConverter;
import com.example.puzzlegame.basededatos.typeconverters.LevelConverter;
import com.example.puzzlegame.basededatos.typeconverters.PiecesConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Fts4
@Entity(tableName = "gameSessions")
@TypeConverters({LevelConverter.class, ImageConverter.class, PiecesConverter.class})
public class GameSession implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "rowid")
    public int id;
    public Level gameLvl;
    public Image bgImage;
    public long endTime;
    @Ignore
    public List<Piece> pieces;
    public List<PieceData> pieceDataList;
    public GameSession() {
    }

    public GameSession(Level level) {
        this.gameLvl = level;
        this.endTime = 0L;
        this.pieces = new ArrayList<>();
    }

    public void setId(int id){
        this.id = id;
    }

    public long getId(){
        return id;
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

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
}

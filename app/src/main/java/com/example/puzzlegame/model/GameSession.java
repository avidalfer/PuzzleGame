package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.PiecesConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity(tableName = "gameSessions",
foreignKeys = {
        @ForeignKey(entity = Level.class,
        parentColumns = "levelId",
        childColumns = "gameLvlId",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),

        @ForeignKey(entity = Image.class,
        parentColumns = "imgId",
        childColumns = "imageId",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "userId",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)
})
public class GameSession implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "gameId")
    public long id;
    public long endTime;
    public int gameLvlId;
    public int imageId;
    public int userId;
    @TypeConverters(PiecesConverter.class)
    public List<PieceData> pieceDataList;

    @Ignore
    private User user;
    @Ignore
    public List<Piece> pieces;
    @Ignore
    public Level gameLvl;
    @Ignore
    public Image bgImage;


    public GameSession() {
    }

    public GameSession(Level level) {
        setGameLvl(level);
        setEndTime(0);
        this.pieces = new ArrayList<>();
    }

    public void setId(long id){
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
        this.imageId = bgImage.imgId;
    }

    public Level getGameLvl() {
        return gameLvl;
    }

    public void setGameLvl(Level gameLvl) {
        this.gameLvl = gameLvl;
        this.gameLvlId = gameLvl.levelId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getIdUser();
    }
}

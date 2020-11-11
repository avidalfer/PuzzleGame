package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.relations.GameSessionWithPieces;
import com.example.puzzlegame.model.relations.GameSessionsAndLevels;
import com.example.puzzlegame.model.PieceData;
import com.example.puzzlegame.model.relations.UserWithGameSessions;

import java.util.List;

@Dao
public abstract class GameSessionDAO {
    @Transaction
    @Query("SELECT * FROM gameSessions")
    public abstract List<GameSessionWithPieces> getGameSessionsWithPieces();

    @Transaction
    @Query("SELECT * FROM users")
    public abstract List<UserWithGameSessions> getGameSessionsByUser();

    @Transaction
    @Query("SELECT * FROM users WHERE name LIKE :name")
    public abstract List<UserWithGameSessions> getUserGameSessions(String name);

    @Transaction
    @Query("SELECT * FROM levels")
    public abstract List<GameSessionsAndLevels> getLevelsAndGameSessions();

    @Transaction
    public long insertGameSession(GameSession gameSession){
        final long gameSessionId = insert(gameSession);

        for (PieceData pieceData : gameSession.pieceDataList) {
            pieceData.gameSessionId = gameSessionId;
            insert(pieceData);
        }
        return gameSessionId;
    }

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    protected abstract void insert(PieceData pieceData);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    protected abstract long insert(GameSession gameSession);

    @Delete
    public abstract void deleteGame(GameSession currentGame);
}

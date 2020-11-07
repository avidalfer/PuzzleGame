package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.GameSessionWithPieces;
import com.example.puzzlegame.model.GameSessionsAndLevels;
import com.example.puzzlegame.model.UserWithGameSessions;

import java.util.List;

@Dao
public interface GameSessionDAO {
    @Transaction
    @Query("SELECT * FROM gameSessions")
    List<GameSessionWithPieces> getGameSessionsWithPieces();

    @Transaction
    @Query("SELECT * FROM users")
    List<UserWithGameSessions> getGameSessionsByUser();

    @Transaction
    @Query("SELECT * FROM users WHERE name LIKE :name")
    List<UserWithGameSessions> getUserGameSessions(String name);

    @Transaction
    @Query("SELECT * FROM levels")
    List<GameSessionsAndLevels> getLevelsAndGameSessions();

}

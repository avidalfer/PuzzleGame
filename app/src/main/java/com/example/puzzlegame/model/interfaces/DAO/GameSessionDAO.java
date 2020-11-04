package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.GameSession;

import java.util.List;

@Dao
public interface GameSessionDAO {
    @Query("SELECT * FROM GameSession")
    List<GameSession> getAll();

    @Query("SELECT * FROM GameSession WHERE id IN (:sessionId)")
    List<GameSession> loadAllByIds(int[] sessionId);

    @Query("SELECT * FROM GameSession WHERE user LIKE :user")
    GameSession findByName(String user);

    @Insert
    void insertAll(GameSession... gameSessions);

    @Delete
    void delete(GameSession GameSession);
}

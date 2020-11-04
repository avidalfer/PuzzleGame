package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.Score;

import java.util.List;

@Dao
public interface HallOfFameDAO {
    @Query("SELECT * FROM Score")
    List<Score> getAll();

    @Query("SELECT * FROM Score WHERE id IN (:id)")
    List<Score> loadAllByIds(int[] id);

    @Query("SELECT * FROM Score WHERE winnerName LIKE :winnerName")
    Score findByName(String winnerName);

    @Insert
    void insertAll(Score... Scores);

    @Delete
    void delete(Score Score);
}

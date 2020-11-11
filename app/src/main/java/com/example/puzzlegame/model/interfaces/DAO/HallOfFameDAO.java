package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.Score;

import java.util.List;

@Dao
public interface HallOfFameDAO {
    @Query("SELECT * FROM scores")
    List<Score> getAll();

    @Insert(entity = Score.class)
    void insertScore(Score... Scores);

    @Delete(entity = Score.class)
    void delete(Score Score);

}

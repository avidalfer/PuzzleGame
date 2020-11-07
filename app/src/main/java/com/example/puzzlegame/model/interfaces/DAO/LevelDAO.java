package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.puzzlegame.model.Level;

import java.util.List;

@Dao
public interface LevelDAO {

    @Query("select * from levels")
    List<Level> getAll();

    @Query("select * from levels where rowid = :levelId")
    Level getLevel(int levelId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLevels(Level...levels);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevel(Level level);

    @Delete
    void deleteLevel(Level level);
}

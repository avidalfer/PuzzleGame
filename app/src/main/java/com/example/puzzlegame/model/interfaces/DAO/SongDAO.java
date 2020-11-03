package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.Song;

import java.util.List;

@Dao
public interface SongDAO {
    @Query("SELECT * FROM Song")
    List<Song> getAll();

    @Query("SELECT * FROM Song WHERE source IN (:source)")
    List<Song> loadAllByIds(int[] source);

    @Query("SELECT * FROM Song WHERE name LIKE :name LIMIT 1")
    Song findByName(String name);

    @Insert
    void insertAll(Song... Songs);

    @Delete
    void delete(Song Song);
}

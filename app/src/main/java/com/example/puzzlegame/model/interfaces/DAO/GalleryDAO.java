package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.User;

import java.util.List;

@Dao
public interface GalleryDAO {
    @Query("SELECT * FROM Image")
    List<Image> getAll();

    @Query("SELECT * FROM Image WHERE imgName IN (:imgNames)")
    List<Image> loadAllByIds(int[] imgNames);

    @Query("SELECT * FROM Image WHERE imgName LIKE :name")
    Image findByName(String name);

    @Insert
    void insertAll(Image... Images);

    @Delete
    void delete(Image Image);
}

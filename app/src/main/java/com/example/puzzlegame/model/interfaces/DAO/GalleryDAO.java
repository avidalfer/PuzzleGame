package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.puzzlegame.model.Image;

import java.util.List;

@Dao
public interface GalleryDAO {
    @Query("SELECT * FROM Images")
    List<Image> getAllImages();

    @Query("SELECT * FROM Images WHERE imgName = :name limit 1")
    Image findByName(String name);

    @Insert
    void insertImages(Image... Images);

    @Delete
    void delete(Image Image);
}

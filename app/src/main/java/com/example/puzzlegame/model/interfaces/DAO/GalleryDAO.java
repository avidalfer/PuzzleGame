package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.Image;

import java.util.List;

@Dao
public abstract class GalleryDAO {
    @Query("SELECT * FROM Images")
    public abstract List<Image> getAllImages();

    @Query("SELECT * FROM Images WHERE imgName = :name limit 1")
    public abstract Image findByName(String name);

    @Transaction
    public long insertImages(Image image){
        final long id = insert(image);
        return id;
    }

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    protected abstract long insert(Image image);

    @Delete
    abstract void delete(Image Image);
}

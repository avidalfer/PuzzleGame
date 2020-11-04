package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.repository.GameAppRepository;

import java.util.List;

@Dao
public interface GameAppDAO {
    @Query("SELECT * FROM GameApp")
    List<GameApp> getAll();

    @Query("SELECT * FROM GameApp WHERE user IN (:user)")
    java.util.List<GameApp> loadAllByIds(int[] user);

    @Query("SELECT * FROM GameApp WHERE user LIKE :user")
    GameAppRepository findByName(String user);

    @Insert
    void insertAll(GameApp... GameApps);

    @Delete
    void delete(GameApp GameApp);
    }

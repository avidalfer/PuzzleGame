package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.User;

@Dao
public interface AppDAO {

    @Query("select user from Gameapp")
    User getCurrentUser();

    @Query("update GameApp set user = :user")
    void setCurrentUser(User user);

    @Query("select * from GameApp")
    GameApp getGameAppData();
}

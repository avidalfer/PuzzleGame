package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.UserConverter;
import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.User;

@Dao
@TypeConverters({UserConverter.class})
public interface AppDAO {

    @Query("select user from Gameapp")
    User getCurrentUser();

    @Query("update GameApp set user = :user")
    void setCurrentUser(User user);

    @Query("select * from GameApp")
    GameApp getGameAppData();
}

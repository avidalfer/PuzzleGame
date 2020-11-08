package com.example.puzzlegame.model.interfaces.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.User;

@Dao
public abstract class AppDAO {

    @Transaction
    public User getCurrentUser(){
        return getUserById(getCurrentUserId());
    }

    @Query("select * from users where userId = :currentUserId")
    protected abstract User getUserById(int currentUserId);

    @Query("select currentUser from gameApp")
    public abstract int getCurrentUserId();

    @Transaction
    public void setCurrentUser(User user){
        int userId = user.getIdUser();
        updateUser(userId);
    }

    @Query("update gameApp set currentUser = :userId")
    public abstract void updateUser(int userId);

    @Query("select * from gameApp")
    public abstract GameApp getGameAppData();

    @Insert
    public abstract void setGameAppData(GameApp gameApp);
}

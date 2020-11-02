package com.example.puzzlegame.basededatos;

import androidx.room.RoomDatabase;

import com.example.puzzlegame.model.interfaces.DAO.UserDAO;

//@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDAO userDAO();

}

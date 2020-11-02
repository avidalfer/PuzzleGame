package com.example.puzzlegame.basededatos;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.puzzlegame.model.User;
import com.example.puzzlegame.model.interfaces.DAO.UserDAO;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();

}

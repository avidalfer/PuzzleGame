package com.example.puzzlegame.basededatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.puzzlegame.model.Song;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.model.interfaces.DAO.SongDAO;
import com.example.puzzlegame.model.interfaces.DAO.UserDAO;


@Database(entities = {User.class, Song.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase;

    private AppDataBase(){

    }

    public static AppDataBase getAppDataBase(AppCompatActivity activity) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(activity.getApplicationContext(),
                    AppDataBase.class, "database-name").build(); {
            };
        }
        return appDataBase;
    }

    public abstract UserDAO userDAO();
    public abstract SongDAO songDAO();

}

package com.example.puzzlegame.basededatos;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.model.interfaces.DAO.AppDAO;
import com.example.puzzlegame.model.interfaces.DAO.GalleryDAO;
import com.example.puzzlegame.model.interfaces.DAO.LevelDAO;
import com.example.puzzlegame.model.interfaces.DAO.UserDAO;


@Database(entities = {
        User.class,
        Level.class,
        Image.class,
        GameSession.class,
        Piece.class,
        LocalHallOfFame.class,
}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase;

    private AppDataBase() {}

    public static AppDataBase getAppDataBase(Application application) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(application.getApplicationContext(),
                    AppDataBase.class, "database-name").build();
        }
        return appDataBase;
    }

    public abstract UserDAO userDAO();

    //public abstract SongDAO songDAO();

    public abstract LevelDAO levelDAO();

    public abstract GalleryDAO galleryDAO();

    public abstract AppDAO gameAppDAO();
}

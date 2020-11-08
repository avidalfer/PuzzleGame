package com.example.puzzlegame.basededatos;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.PieceData;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.model.interfaces.DAO.AppDAO;
import com.example.puzzlegame.model.interfaces.DAO.GalleryDAO;
import com.example.puzzlegame.model.interfaces.DAO.GameSessionDAO;
import com.example.puzzlegame.model.interfaces.DAO.LevelDAO;
import com.example.puzzlegame.model.interfaces.DAO.UserDAO;


@Database(entities = {
        User.class,
        Level.class,
        Image.class,
        GameSession.class,
        PieceData.class,
        GameApp.class,
        LocalHallOfFame.class,
}, version = 8)
public abstract class AppDataBase extends RoomDatabase {
    protected static AppDataBase appDataBase;

    protected AppDataBase() {}

    public static AppDataBase getAppDataBase(Application application) {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(application.getApplicationContext(),
                    AppDataBase.class, "database-name").fallbackToDestructiveMigration().build();
        }
        return appDataBase;
    }

    public abstract UserDAO userDAO();

    //public abstract SongDAO songDAO();

    public abstract LevelDAO levelDAO();

    public abstract GalleryDAO galleryDAO();

    public abstract AppDAO gameAppDAO();

    public abstract GameSessionDAO gameSessionDAO();
}

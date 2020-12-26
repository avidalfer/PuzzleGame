package com.example.puzzlegame.model.interfaces.DAO;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.MusicSettings;
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

    @Query("update musicsettings set currentSong = :source")
    public abstract void setCurrentSong(Uri source);

    @Query("select currentSong from musicsettings")
    public abstract Uri getCurrentSong();

    @Query("update musicsettings set playingMusic = :state")
    public abstract void setMusicState(Boolean state);

    @Query("update musicsettings set musicVol = :vol")
    public abstract void setVolume(int vol);

    @Query("select musicVol from musicsettings")
    public abstract int getVolume();

    @Query("select playingMusic from musicsettings")
    public abstract boolean getMusicState();

    @Transaction
    public void setDefaultSettings(){
        insertDefaultSettings(new MusicSettings());
    }
    @Insert
    protected abstract void insertDefaultSettings(MusicSettings musicSettings);

    @Query("select * from musicsettings limit 1")
    public abstract MusicSettings getSettings();
}

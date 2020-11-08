package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "gameApp")
public class GameApp implements Serializable {

    @PrimaryKey
    public Integer appId;
    @ColumnInfo(name = "currentUser")
    public int currentUserId;
    public int songId;

    //from musicSettings.class see https://developer.android.com/reference/androidx/room/Insert
    public boolean playingMusic;
    public double musicVol;
    @Ignore
    private List<User> users;

    public GameApp() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}

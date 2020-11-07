package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.puzzlegame.basededatos.typeconverters.LevelConverter;
import com.example.puzzlegame.basededatos.typeconverters.UserConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Fts4
@Entity(tableName = "gameApp")
@TypeConverters({LevelConverter.class, UserConverter.class, LevelConverter.class})
public class GameApp implements Serializable {

    private static GameApp gameApp;
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    public Integer id;
    public User user;
    public Level userLvl;
    public List<User> users;
    public List<Level> levels;

    public GameApp(){}

    private GameApp(Level userLvl, List<User> users){
        this.userLvl = userLvl;
        if (users != null) {
            this.users = users;
        } else {
            this.users = new ArrayList<>();
        }
        levels = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public static GameApp getGameApp() {
        return gameApp;
    }

    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        this.users.add(user) ;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Level getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(Level userLvl) {
        this.userLvl = userLvl;
    }
}

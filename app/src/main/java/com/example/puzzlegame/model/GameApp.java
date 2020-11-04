package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameApp implements Serializable {

    private static GameApp gameApp;
    @PrimaryKey
    private User user;
    @ColumnInfo(name = "level")
    private Level userLvl;
    @ColumnInfo(name = "users")
    private ArrayList<User> users;
    @ColumnInfo(name = "levels")
    private ArrayList<Level> levels;
    public ArrayList<User> getUsers() {
        return users;
    }

    public static GameApp getGameApp() {
        if (gameApp == null) {

        }
        return gameApp;
    }

    private GameApp(Level userLvl, ArrayList<User> users){
        this.userLvl = userLvl;
        this.users = users;
        levels = new ArrayList<>();
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

    public List<Level> getLevels() {
        return levels;
    }

    public Level getLevelById(int i) {
        return levels.get(i);
    }

    public void setLevels(List<Level> levels) {
        this.levels = (ArrayList<Level>) levels;
    }

    public void addLevel(Level newLevel) {
        this.levels.add(newLevel);
    }

    public int removeLevel(Level level) {
        this.levels.remove(level);
        return levels.size();
    }

    public Level getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(Level userLvl) {
        this.userLvl = userLvl;
    }
}

package com.example.puzzlegame.model;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameApp implements Serializable {
    private static GameApp gameApp;
    private User user;
    private ArrayList<User> users;
    private ArrayList<Level> levels;
    public ArrayList<User> getUsers() {
        return users;
    }

    public static GameApp getGameApp() {
        if (gameApp == null) {
            gameApp = new GameApp();
        }
        return gameApp;
    }

    private GameApp(){
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
}

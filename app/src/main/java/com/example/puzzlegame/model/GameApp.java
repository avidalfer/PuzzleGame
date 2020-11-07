package com.example.puzzlegame.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameApp implements Serializable {

    private static GameApp gameApp;
    @PrimaryKey
    private int id;
    private User user;
    private Level userLvl;
    @Ignore
    private final ArrayList<User> users;
    @Ignore
    private ArrayList<Level> levels;
    private HallOfFame hallOfFame;
    @Ignore
    public ArrayList<User> getUsers() {
        return users;
    }

    private GameApp(Level userLvl, ArrayList<User> users){
        this.userLvl = userLvl;
        if (users != null) {
            this.users = users;
        } else {
            this.users = new ArrayList<>();
        }
        levels = new ArrayList<>();
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

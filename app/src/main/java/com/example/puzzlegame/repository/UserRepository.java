package com.example.puzzlegame.repository;

import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.User;

public class UserRepository {
    private User user;
    private Level userLevel;

    public UserRepository(){
        if (getUser() == null) {
            user = new User(1, "Racoon", Language.EN);
        }
    }

    public User getUser() {
        //Get user from retrofit to SQLite an get User from SQLite.
        user = new User(1, "Mapache", Language.ES);
        return user;
    }

    public Level getUserLevel() {
        userLevel = user.getUserLvl();
        return userLevel;
    }
}

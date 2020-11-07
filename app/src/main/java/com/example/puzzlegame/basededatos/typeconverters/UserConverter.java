package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserConverter {

    @TypeConverter
    public static User stringToUser(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        User user = gson.fromJson(json, type);
        return user;
    }

    @TypeConverter
    public static String userToString(User user){
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        String json = gson.toJson(user, type);
        return json;
    }

    @TypeConverter
    public static List<User> stringToUsers(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(json, type);
        return users;
    }

    @TypeConverter
    public static String usersToString(List<User> users) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {}.getType();
        String json = gson.toJson(users, type);
        return json;
    }
}

package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.Level;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LevelConverter {
    @TypeConverter
    public static Level stringToLevel(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Level>() {}.getType();
        Level level = gson.fromJson(json, type);
        return level;
    }

    @TypeConverter
    public static String levelToString(Level level) {
        Gson gson = new Gson();
        Type type = new TypeToken<Level>() {}.getType();
        String json = gson.toJson(level, type);
        return json;
    }

    @TypeConverter
    public static List<Level> stringToLevels(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Level>>() {}.getType();
        List<Level> levels = gson.fromJson(json, type);
        return levels;
    }

    @TypeConverter
    public static String levelsToString(List<Level> levels) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Level>>() {}.getType();
        String json = gson.toJson(levels, type);
        return json;
    }
}

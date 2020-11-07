package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.GameSession;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GameSessionConverter {
    @TypeConverter
    public static GameSession stringToGameSession(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<GameSession>() {}.getType();
        GameSession gameSession = gson.fromJson(json, type);
        return gameSession;
    }

    @TypeConverter
    public static String gameSessionToString(GameSession gameSession){
        Gson gson = new Gson();
        Type type = new TypeToken<GameSession>() {}.getType();
        String json = gson.toJson(gameSession, type);
        return json;
    }

    @TypeConverter
    public static List<GameSession> stringToGameSessions(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<GameSession>>() {}.getType();
        List<GameSession> gameSessions = gson.fromJson(json, type);
        return gameSessions;
    }

    @TypeConverter
    public static String stringToGameSessions(List<GameSession> gameSessions){
        Gson gson = new Gson();
        Type type = new TypeToken<List<GameSession>>() {}.getType();
        String json = gson.toJson(gameSessions, type);
        return json;
    }
}

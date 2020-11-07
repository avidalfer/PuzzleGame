package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.PieceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PiecesConverter {

    @TypeConverter
    public static List<PieceData> stringToPieces(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<PieceData>>() {}.getType();
        List<PieceData> pieces = gson.fromJson(json, type);
        return pieces;
    }

    @TypeConverter
    public static String piecesToString(List<PieceData> pieces){
        Gson gson = new Gson();
        Type type = new TypeToken<List<PieceData>>() {}.getType();
        String json = gson.toJson(pieces, type);
        return json;
    }
}

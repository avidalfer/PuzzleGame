package com.example.puzzlegame.basededatos.typeconverters;

import androidx.room.TypeConverter;

import com.example.puzzlegame.model.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ImageConverter {

    @TypeConverter
    public static Image stringToImage(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Image>() {}.getType();
        Image image = gson.fromJson(json, type);
        return image;
    }

    @TypeConverter
    public static String imageToString(Image image){
        Gson gson = new Gson();
        Type type = new TypeToken<Image>() {}.getType();
        String json = gson.toJson(image, type);
        return json;
    }
}

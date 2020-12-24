package com.example.puzzlegame.basededatos.typeconverters;

import android.net.Uri;

import androidx.room.TypeConverter;

public class UriConverter {
    @TypeConverter
    public static Uri stringToUri(String json){
        if (json.equals("{}") || json.isEmpty()){
            return null;
        }
        Uri uri = Uri.parse(json);
        return uri;
    }

    @TypeConverter
    public static String levelToString(Uri uri) {
        if (uri == null) {
            return "";
        }
        String json = uri.toString();
        return json;
    }
}

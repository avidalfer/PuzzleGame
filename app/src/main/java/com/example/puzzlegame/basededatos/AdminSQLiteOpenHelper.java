package com.example.puzzlegame.basededatos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DBMapuzzle) {
        BaseDeDatos.execSQL("create_table user(idUser long primary key, name String, userLvl String," +
                "language String, currentGameSession String, playedGames String, gameApp String)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DBMapuzzle, int oldVersion, int newVersion) {

    }
}

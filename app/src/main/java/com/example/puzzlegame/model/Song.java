package com.example.puzzlegame.model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song {
    @PrimaryKey
    public Integer songId;

    public Uri source;

    public String name;

    public Song(Uri source, String title) {
        this.source = source;
        this.name = title;
    }
    public Song() { }

    public Uri getSource() {
        return source;
    }

    public String getName() {
        return name;
    }
}

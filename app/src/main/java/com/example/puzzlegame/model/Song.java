package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Song {
    @ColumnInfo (name = "source")
    private String source;

    @ColumnInfo(name = "name")
    private String name;

    public Song(String source, String name) {
        this.source = source;
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

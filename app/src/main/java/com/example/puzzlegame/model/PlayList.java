package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.List;

@Entity
public class PlayList {

    @ColumnInfo(name = "playList")
    private static PlayList playList;
    @ColumnInfo(name = "listOfSongs")
    private List<Song> listOfSongs;

    private PlayList(){ }

    public static PlayList getPlayList () {
        if (playList == null) {
            playList = new PlayList();
        }
        return playList;
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public void setListOfSongs(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }
}

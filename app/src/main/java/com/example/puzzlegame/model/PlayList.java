package com.example.puzzlegame.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;

import java.util.List;

@Entity
public class PlayList {


    private static PlayList playList;
    @Embedded
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

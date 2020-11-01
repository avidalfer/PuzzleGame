package com.example.puzzlegame.model;

import java.util.List;

public class PlayList {

    private static PlayList playList;
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

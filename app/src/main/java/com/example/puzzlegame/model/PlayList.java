package com.example.puzzlegame.model;

import java.util.List;

public class PlayList {

    private List<Song> playList;

    public PlayList(List<Song> playList) {
        this.playList = playList;
    }

    public List<Song> getPlayList() {
        return playList;
    }

    public void setPlayList(List<Song> playList) {
        this.playList = playList;
    }
}

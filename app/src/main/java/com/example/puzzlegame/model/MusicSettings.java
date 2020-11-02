package com.example.puzzlegame.model;

import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class MusicSettings {
    @ColumnInfo(name = "playingMusic")
    private boolean playingMusic;
    @ColumnInfo(name = "musicVol")
    private double musicVol;

    public MusicSettings(boolean playingMusic, double musicVol) {
        this.playingMusic = playingMusic;
        this.musicVol = musicVol;
    }

    public boolean isPlayingMusic() {
        return playingMusic;
    }

    public void setPlayingMusic(boolean play) {
        this.playingMusic = play;
    }

    public double getMusicVol() {
        return musicVol;
    }

    public void setMusicVol(double musicVol) {
        this.musicVol = musicVol;
    }
}

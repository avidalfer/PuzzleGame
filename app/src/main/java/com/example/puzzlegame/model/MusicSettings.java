package com.example.puzzlegame.model;

import androidx.lifecycle.MutableLiveData;

public class MusicSettings {
    private boolean playingMusic;
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

package com.example.puzzlegame.model;

public class Music {
    private boolean playingMusic;
    private int musicVol;

    public Music(boolean playingMusic, int musicVol) {
        this.playingMusic = playingMusic;
        this.musicVol = musicVol;
    }

    public boolean isPlayingMusic() {
        return playingMusic;
    }

    public void setPlayingMusic(boolean playingMusic) {
        this.playingMusic = playingMusic;
    }

    public int getMusicVol() {
        return musicVol;
    }

    public void setMusicVol(int musicVol) {
        this.musicVol = musicVol;
    }
}

package com.example.puzzlegame.model;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MusicSettings {

    @PrimaryKey
    public int musicSettingsId;
    @ColumnInfo(defaultValue = "1")
    public boolean playingMusic = true;
    @ColumnInfo(defaultValue = "5")
    public int musicVol = 5;
    @ColumnInfo(defaultValue = "main")
    public Uri currentSong = Uri.parse("main");

    public MusicSettings(){}

    public MusicSettings(boolean playingMusic, int musicVol, Uri currentSong) {
        this.playingMusic = playingMusic;
        this.musicVol = musicVol;
        this.currentSong = currentSong;
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

    public void setMusicVol(int musicVol) {
        this.musicVol = musicVol;
    }

    public void setCurrentSong(Uri currentSong) {
        this.currentSong = currentSong;
    }

    public Uri getCurrentSong() {
        return currentSong;
    }
}

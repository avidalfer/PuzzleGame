package com.example.puzzlegame.common;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Song;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MediaPlayerController {
    private static MediaPlayerController Instance;
    private static MediaPlayer mediaPlayer;
    private static Song _song;
    private Context context;
    private Activity act;
    private boolean playingMainTheme;

    private MediaPlayerController() {
    }

    public static MediaPlayerController getInstance() {
        if (Instance == null) {
            Instance = new MediaPlayerController();
        }
        return Instance;
    }

    public void init(Activity act) {
        this.act = act;
        this.context = act.getApplicationContext();

    }

    public void setCurrentSong(Song nsong) {
        if (_song != null) {
           if (_song == nsong) {
               return;
           }
        }
        _song = nsong;
        startSong();
    }

    public void playRawTheme() {
        if (mediaPlayer != null) {
            releaseMusicPlayer();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.main_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        playingMainTheme = true;
        _song = null;
    }

    public void pauseMusic() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    public void releaseMusicPlayer() {
        if (mediaPlayer == null) return;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void resumeSong() {
        if (mediaPlayer == null) return;

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void startSong(){
        if (_song != null) {
            try {
                startStreamSong(_song);
            } catch (IOException ex) {
                Log.d(TAG, "startSong: " + ex.getMessage());
                playingMainTheme = true;
                _song = null;
            }
            return;
        }
        playRawTheme();
    }

    public void startStreamSong(Song nsong) throws IOException {
        if (nsong != null)
            _song = nsong;
        releaseMusicPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(act, _song.source);
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                playingMainTheme = false;
            }
        });
        Toast.makeText(context, act.getString(R.string.play_music), Toast.LENGTH_SHORT).show();
    }

    public void setVolume(int progress) {
        AudioManager manager = (AudioManager) act.getSystemService(Context.AUDIO_SERVICE);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    public void setMediaPlayerListener() {
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                playRawTheme();
//            }
//        });
    }

    public boolean isMainTheme() {
        return playingMainTheme;
    }
}

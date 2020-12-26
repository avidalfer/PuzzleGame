package com.example.puzzlegame.ui.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.Help;
import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Song;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;
import com.example.puzzlegame.ui.settings.SettingsFragment;
import com.example.puzzlegame.ui.settings.SettingsViewModel;

import java.io.IOException;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private boolean showingSettings;
    private SettingsFragment settingsFragment;
    private Chronometer timer;
    private MediaPlayer mediaPlayer;
    private GameAppViewModel gameAppViewModel;
    private SettingsViewModel settingsViewModel;

    protected void begin() {
        gameAppViewModel = new ViewModelProvider(this).get(GameAppViewModel.class);
        gameAppViewModel.begin(this);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        beginListeners();
    }

    private void beginListeners() {
        final Observer<Boolean> MusicState = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean musicState) {
                if (musicState) {
                    restartSong();
                } else {
                    pauseMusic();
                }
            }
        };
        settingsViewModel.getMusicStateObservable().observe(this, MusicState);

        final Observer<Song> lastPlayedSongObserver = new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                changeSong(song);
            }
        };
        settingsViewModel.getLastPlayedSongObservable().observe(this, lastPlayedSongObserver);

        final Observer<Boolean> musicStateObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean state) {
                if (!state) {
                    pauseMusic();
                }
            }
        };
        settingsViewModel.getMusicStateObservable().observe(this, musicStateObserver);

        final Observer<Integer> volumeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer vol) {
                setVolume(vol);
            }
        };
        settingsViewModel.getVolumeObservable().observe(this, volumeObserver);
    }

    public void playRawTheme() {
        if (mediaPlayer != null) {
            releaseMusicPlayer();
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.main_theme);
        if (mediaPlayer == null) {
            Toast.makeText(this, getString(R.string.No_AudioFile), Toast.LENGTH_SHORT).show();
            return;
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void pauseMusic() {
        if (mediaPlayer == null) return;

        mediaPlayer.pause();
    }

    protected void releaseMusicPlayer() {
        if (mediaPlayer == null) return;

        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void startMusic() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changeHomeIcon(R.drawable.ic_baseline_menu_open_24);
        showingSettings = false;
        if (timer.getVisibility() == View.VISIBLE) {
            PuzzleGameActivity.resumeTimer();
        }
    }

    /**
     * create menu from app menu resource
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        showingSettings = false;
        settingsFragment = new SettingsFragment();
        timer = findViewById(R.id.timer);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Contains the logic from menu items clicked.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            if (!showingSettings) {
                String Tag = "settings";
                changeHomeIcon(R.drawable.ic_baseline_arrow_back_ios_24);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.comming_in_anim, R.anim.comming_out_anim);
                ft.addToBackStack(Tag);
                ft.replace(R.id.settings, settingsFragment, Tag);
                ft.commitAllowingStateLoss();

                if (timer.getVisibility() == View.VISIBLE) {
                    PuzzleGameActivity.pauseTimer();
                }
                showingSettings = true;
            } else {
                onBackPressed();
            }
            return true;
        } else if (item.getItemId() == R.id.action_help) {
            startActivity(new Intent(getApplicationContext(), Help.class));
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Action not implemented", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeHomeIcon(@DrawableRes int drawableIcon) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(drawableIcon);
    }

    public void restartSong() {
        if (mediaPlayer == null) {
           // Song song = settingsViewModel.getLastPlayedSongObservable().getValue();
        //    if (song == null || song.name.equals("main"))
        //        playRawTheme();
            return;
        }
        if (!mediaPlayer.isPlaying()) {
            Song song = settingsViewModel.getLastPlayedSongObservable().getValue();
            if (song == null) {
                playRawTheme();
            } else
            {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(this,song.source);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    playRawTheme();
                }
            }
        }
    }

    public void changeSong(Song song) {
        if (mediaPlayer != null){
            releaseMusicPlayer();
        }
        try {
            if (song == null) {
                playRawTheme();
                return;
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getApplicationContext(), song.getSource());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(this, getString(R.string.play_music), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(int progress) {
        AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isApplicationBroughtToBackground(this))
            pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isAppOnForeground(this))
            restartSong();
    }

    public boolean isAppOnForeground(final Context context)
    {
        final ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        final String packageName = context.getPackageName();
        for (final ActivityManager.RunningAppProcessInfo appProcess : appProcesses)
        {
            if ((appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                && appProcess.processName.equals(packageName))
                return true;
        }
        return false;
    }

    public static boolean isApplicationBroughtToBackground(final Context context)
    {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty())
        {
            final ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName()))
                return true;
        }
        return false;
    }
}
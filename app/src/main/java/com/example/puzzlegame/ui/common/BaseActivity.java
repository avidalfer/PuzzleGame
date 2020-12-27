package com.example.puzzlegame.ui.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.Help;
import com.example.puzzlegame.R;
import com.example.puzzlegame.common.MediaPlayerController;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;
import com.example.puzzlegame.ui.settings.SettingsFragment;
import com.example.puzzlegame.ui.settings.SettingsViewModel;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private boolean showingSettings;
    private SettingsFragment settingsFragment;
    private Chronometer timer;
    public SettingsViewModel settingsViewModel;
    protected MediaPlayerController mediaPlayer = MediaPlayerController.getInstance();
    private boolean activityStarted = false;

    protected void begin() {
        new ViewModelProvider(this).get(GameAppViewModel.class).begin(this);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.initRepository(this);
    }

    protected void beginMedia() {
        mediaPlayer.init(this);
        if (settingsViewModel.getMusicStateObservable().getValue() != null && settingsViewModel.getMusicStateObservable().getValue()) {
            mediaPlayer.setCurrentSong(settingsViewModel.getLastPlayedSongObservable().getValue());

            if (settingsViewModel.getVolumeObservable().getValue() != null) {
                mediaPlayer.setVolume(settingsViewModel.getVolumeObservable().getValue());
            }

            if (mediaPlayer.isMainTheme()) {
                mediaPlayer.setCurrentSong(null);
                settingsViewModel.setCurrentSong(null);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        beginListeners();
    }

    private void beginListeners() {
        if (settingsViewModel == null) {
            begin();
        }
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
     *
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

    @Override
    protected void onPause() {
        super.onPause();
        if (isApplicationBroughtToBackground(this))
            mediaPlayer.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isAppOnForeground(this))
            mediaPlayer.resumeSong();
    }

    public boolean isAppOnForeground(final Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        final String packageName = context.getPackageName();
        for (final ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if ((appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                    && appProcess.processName.equals(packageName))
                return true;
        }
        return false;
    }

    public static boolean isApplicationBroughtToBackground(final Context context) {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            final ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName()))
                return true;
        }
        return false;
    }
}
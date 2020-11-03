package com.example.puzzlegame.ui.common;

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

import com.example.puzzlegame.Help;
import com.example.puzzlegame.R;
import com.example.puzzlegame.ScrollingFragmentOptions;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;

public class BaseActivity extends AppCompatActivity {

    private boolean showingSettings;
    private ScrollingFragmentOptions settingsFragment;
    private Chronometer timer;

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
        settingsFragment = new ScrollingFragmentOptions();
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
}
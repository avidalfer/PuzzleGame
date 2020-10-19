package com.example.puzzlegame.common;

import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.puzzlegame.R;

/**
 * Contains static functions for common activities behaviour (i.e. create a common toolbar)
 */
public class Utils {

    //common static toolbar functions
    /**
     * Sets toolbar as a main activity actionBar
     * @param activity
     * @return
     */
    public static Toolbar createToolbar (AppCompatActivity activity) {

        Toolbar toolbar;

        toolbar = activity.findViewById(R.id.main_toolbar);
        activity.setSupportActionBar(toolbar);

        return toolbar;
    }

    /**
     * Default config for actionbar
     * - disable title
     * - enable homeButton
     * - set different icon for homeButton
     * @param activity
     * @return
     */
    public static ActionBar configDefaultAppBar(AppCompatActivity activity) {

        ActionBar actionBar = null;
        try {
            if (activity.getSupportActionBar() != null) {
                actionBar = activity.getSupportActionBar();

                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_open_24);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } catch (NullPointerException e) {
            Log.d("Error", "Error on SupportAppBar config. Not finalized");
        }

        return actionBar;
    }
}

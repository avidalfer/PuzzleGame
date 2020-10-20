package com.example.puzzlegame.common;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.puzzlegame.R;

import static android.R.color.white;

/**
 * Contains static functions for common activities behaviour (i.e. create a common toolbar)
 */
public class Utils {

    //common static toolbar functions
    /**
     * Sets toolbar as a main activity actionBar
     * Sets color item icons to white
     * @param activity
     * @return
     */
    public static Toolbar createToolbar (AppCompatActivity activity) {

        Toolbar toolbar;

        toolbar = activity.findViewById(R.id.main_toolbar);
        activity.setSupportActionBar(toolbar);

        MenuItem item;
        for (int i = 0; i < toolbar.getMenu().size(); i++) {
             item = toolbar.getMenu().getItem(i);
            final Drawable icon = item.getIcon();
            icon.setColorFilter(activity.getResources().getColor(white), PorterDuff.Mode.SRC_ATOP);
        }
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
                actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_open_24);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } catch (NullPointerException e) {
            Log.d("Error", "Error on SupportAppBar config. Not finalized");
        }

        return actionBar;
    }
}

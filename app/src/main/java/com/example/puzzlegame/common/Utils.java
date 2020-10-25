package com.example.puzzlegame.common;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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

        setTitleTextClickListener(activity);
        return toolbar;
    }

    public static void setTitleTextClickListener(final AppCompatActivity activity) {
        final ImageButton titleImg = activity.findViewById(R.id.title);

        titleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to HomeActivity
                final Animation bounceAnim = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.bounce_anim);
                BounceInterpolation interpolator = new BounceInterpolation(0.2, 20);
                bounceAnim.setInterpolator(interpolator);
                titleImg.startAnimation(bounceAnim);

                Toast t = Toast.makeText(activity.getApplicationContext(), "Calling to HomeActivity", Toast.LENGTH_SHORT);
                t.show();
            }
        });
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

    public static void TODO(AppCompatActivity activity, View view) {
        Toast toast = Toast.makeText(activity.getApplicationContext(), "Function not implemented yet", Toast.LENGTH_SHORT);
        toast.show();
    }
}

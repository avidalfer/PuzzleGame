package com.example.puzzlegame.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.puzzlegame.R;

public class WinActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);
    }

    public void goToHoF(View view) {

        Intent intent = new Intent(this, HallOfFameActivity.class);
        startActivity(intent);

    }
}
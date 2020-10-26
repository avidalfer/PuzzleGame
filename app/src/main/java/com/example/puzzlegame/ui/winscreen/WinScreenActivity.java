package com.example.puzzlegame.ui.winscreen;

import android.content.Intent;
import android.os.Bundle;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.HallOfFameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.Choreographer;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.puzzlegame.R;

public class WinScreenActivity extends BaseActivity {

    private WinScreenViewModel winScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setViews();
        setListeners();
    }

    private void setViews() {
        winScreenViewModel = new ViewModelProvider(this).get(WinScreenViewModel.class);

        long winTime = getWinTime();
        TextView chronometer = findViewById(R.id.time);
        String formattedWinTime = Utils.FormatTime(winTime);
        chronometer.setText(formattedWinTime);
        }

    private long getWinTime() {
        return winScreenViewModel.getWinTime();

    }


    private void setListeners() {
        Button btnToHOF = findViewById(R.id.btn_toHoF);

        btnToHOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });
    }
}
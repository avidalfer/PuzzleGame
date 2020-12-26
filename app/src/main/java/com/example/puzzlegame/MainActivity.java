package com.example.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;
import com.example.puzzlegame.ui.selectGame.SelectGameActivity;
import com.example.puzzlegame.ui.settings.SettingsViewModel;

public class MainActivity extends BaseActivity {

    Button btn1, btn2;
    SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.initRepository(this);
        super.begin();
        setViews();
        setListeners();
    }

    /**
     * getDataBase instance for the first time and set all levels if they are not set
     * run on background
     */
    private void setViews() {
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }

    private void setListeners() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SelectGameActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });
    }
}

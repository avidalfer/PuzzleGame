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
import android.widget.Button;
import android.widget.EditText;

import com.example.puzzlegame.R;

public class WinActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setListeners();
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
package com.example.puzzlegame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.SelectGameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenViewModel;

public class MainActivity extends BaseActivity {

    Button btn1, btn2;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        getViews();
        startButtons();
    }

    private void getViews() {
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }

    private void startButtons() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SelectGameActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WinScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}

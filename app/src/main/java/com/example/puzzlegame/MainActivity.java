package com.example.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.SelectGameActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;
import com.example.puzzlegame.ui.common.BaseActivity;

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
        new Thread() {
            @Override
            public void run() {
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(v.getContext(), SelectGameActivity.class));
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(v.getContext(), WinScreenActivity.class));
                    }
                });
            }
        }.start();
    }
}
package com.example.puzzlegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameAppRepository;
import com.example.puzzlegame.ui.SelectGame.SelectGameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;

public class MainActivity extends BaseActivity {

    Button btn1, btn2;
    Button[] buttons;
    GameAppRepository gameAppRepository;
    GalleryRepository galleryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        init();
        setViews();
        setListeners();

    }

    /**
     * getDataBase instance for the first time and set all levels if they are not set
     * run on background
     */
    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                galleryRepository = GalleryRepository.initGalleryRepository(getApplication());
                gameAppRepository = GameAppRepository.initGameAppRepository(getApplication());
                gameAppRepository.getCurrentUser(); // pendiente de actualización con la implementación del login
            }
        }).start();
    }

    private void setViews() {
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }

    private void setListeners() {
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

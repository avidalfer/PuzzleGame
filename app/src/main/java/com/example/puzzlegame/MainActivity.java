package com.example.puzzlegame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Gallery;
import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameAppRepository;
import com.example.puzzlegame.ui.SelectGame.SelectGameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;

public class MainActivity extends BaseActivity {

    Button btn1, btn2;
    Button[] buttons;
    GameAppRepository gameAppRepository;
    GalleryRepository gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        gameAppRepository = GameAppRepository.getGameAppRepository();

        setViews();
        updateGallery();
        startButtons();

    }

    private void updateGallery() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager am = getAssets();
                gallery.updateImageList(am);
            }
        }).start();
    }

    private void setViews() {
        gallery = GalleryRepository.getGalleryRepository();
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

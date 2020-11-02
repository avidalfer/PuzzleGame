package com.example.puzzlegame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlegame.ui.game.PuzzleGameActivity;

public class PushPlay extends AppCompatActivity {
    AnimationDrawable loadingAnimation;
    ImageView loadingImage;
    Button pushPlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_play);
        loadingImage = (ImageView) findViewById(R.id.loadingView);
        loadingImage.setBackgroundResource(R.drawable.loading);
        loadingAnimation = (AnimationDrawable) loadingImage.getBackground();
        pushPlayButton=(Button) findViewById(R.id.pushPlayButton);
        pushPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PuzzleGameActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadingAnimation.start();
    }

}

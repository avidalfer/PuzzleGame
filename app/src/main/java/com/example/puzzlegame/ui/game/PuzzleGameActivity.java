package com.example.puzzlegame.ui.game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzleGameActivity extends BaseActivity {

    private PuzzleGameViewModel gameViewModel;
    private Level levelSelected;
    private RelativeLayout puzzleLayout;
    private ImageView imageView;
    private ArrayList<Piece> totalPieces;
    private Chronometer timer;
    private Bitmap bimapBG;
    private static final String TAG = "PuzzleGameActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        levelSelected = (Level) intent.getSerializableExtra("gameLevel");
        gameViewModel = new ViewModelProvider(this).get(PuzzleGameViewModel.class);

        setViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        long playedTime = gameViewModel.getCurrentPlayedTime();
        timer.setBase(SystemClock.elapsedRealtime() - playedTime);
        timer.start();
        setBackgroundBitmap();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        imageView.setImageBitmap(bimapBG);
        gameViewModel.createPieces(imageView, levelSelected);
        totalPieces = gameViewModel.getpieces();
    }

    private void setViews() {
        puzzleLayout = findViewById(R.id.puzzle_layout);
        imageView = findViewById(R.id.board_view);
        findViewById(R.id.title).setVisibility(View.GONE);
        timer = findViewById(R.id.timer);
        timer.setVisibility(View.VISIBLE);

        setListeners();
    }

    private void setListeners() {
        //LiveData
        final Observer<Image> backgroundImageObserver = new Observer<Image>() {
            @Override
            public void onChanged(Image image) {
                    bimapBG = gameViewModel.getCurrentBitmap();
            }
        };
        gameViewModel.getBoardImageObservable().observe(this, backgroundImageObserver);

        final Observer<List<Piece>> piecesObserver = new Observer<List<Piece>>() {
            @Override
            public void onChanged(List<Piece> pieces) {
                OnPieceListener onPieceTouchListener = new OnPieceListener(gameViewModel);
                for (Piece piece : pieces) {
                    piece.setOnTouchListener(onPieceTouchListener);
                    puzzleLayout.addView(piece);
                    // randomize position, on the bottom of the screen
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) piece.getLayoutParams();
                    lParams.leftMargin = new Random().nextInt(puzzleLayout.getWidth() - piece.getPieceWidth());
                    lParams.topMargin = puzzleLayout.getHeight() - piece.getPieceHeight();
                    piece.setLayoutParams(lParams);
                }
            }
        };
        gameViewModel.getPiecesObservable().observe(this, piecesObserver);
    }

    private long getPlayedTime(){
        return SystemClock.elapsedRealtime() - timer.getBase();
    }

    private void setBackgroundBitmap() {
        gameViewModel.setBackgroundBitmap();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.stop();
        gameViewModel.setCurrentTime(getPlayedTime());
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameViewModel.saveGameStatus();
    }

    public void checkGameOver() {
        if (gameViewModel.checkGameOver()) {
            startActivity(new Intent(getApplicationContext(), WinScreenActivity.class));
        }
    }
}

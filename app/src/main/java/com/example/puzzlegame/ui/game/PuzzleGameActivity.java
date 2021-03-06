package com.example.puzzlegame.ui.game;

import android.app.Activity;
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
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.Piece;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.winscreen.WinScreenActivity;

import java.util.List;
import java.util.Random;

public class PuzzleGameActivity extends BaseActivity {

    private static PuzzleGameViewModel gameViewModel;
    private Level levelSelected;
    private RelativeLayout puzzleLayout;
    private ImageView imageView;
    private static Chronometer timer;
    private Bitmap bitmapBG;
    private Activity act;
    private static final String TAG = "PuzzleGameActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        levelSelected = (Level) intent.getSerializableExtra("gameLevel");
        gameViewModel = new ViewModelProvider(this).get(PuzzleGameViewModel.class);
        act = this;

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);
        setViews(); //and Listeners
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeTimer();
        setBackgroundBitmap();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        imageView.setImageBitmap(bitmapBG);
        gameViewModel.createPieces(imageView, levelSelected);
        gameViewModel.saveGameStatus(getApplication(), levelSelected);
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
        //LiveData background image set
        final Observer<Image> backgroundImageObserver = new Observer<Image>() {
            @Override
            public void onChanged(Image image) {
                    bitmapBG = gameViewModel.getCurrentBitmap();
            }
        };
        gameViewModel.getBoardImageObservable().observe(this, backgroundImageObserver);

        //Background finished charging
        final Observer<List<Piece>> piecesObserver = new Observer<List<Piece>>() {
            @Override
            public void onChanged(List<Piece> pieces) {
                OnPieceListener onPieceTouchListener = new OnPieceListener(act, gameViewModel);
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

        //gameOver
        final Observer<Boolean> gameOverStateObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean gameOver) {
                if (gameOver) {
                    long winTime = pauseTimer();
                    Intent intent = new Intent(getApplicationContext(), WinScreenActivity.class);
                    intent.putExtra("winTime", winTime);
                    startActivity(intent);
                }
            }
        };
        gameViewModel.getGameOverObservable().observe(this, gameOverStateObserver);
    }

    private void setBackgroundBitmap() {
        gameViewModel.setBackgroundBitmap();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
    }

    public static long pauseTimer(){
        timer.stop();
        long playedTime = SystemClock.elapsedRealtime() - timer.getBase();
        gameViewModel.setCurrentTime(playedTime);
        return playedTime;
    }

    public static void resumeTimer(){
        long playedTime = gameViewModel.getCurrentPlayedTime();
        timer.setBase(SystemClock.elapsedRealtime() - playedTime);
        timer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameViewModel.updateGameStatus();
    }
}

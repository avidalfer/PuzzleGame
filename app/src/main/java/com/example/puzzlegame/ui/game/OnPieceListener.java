package com.example.puzzlegame.ui.game;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.puzzlegame.model.Piece;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class OnPieceListener implements View.OnTouchListener {

    private static final int MAX_STREAMS = 5;
    private float xDelta;
    private float yDelta;
    private PuzzleGameViewModel gameViewModel;
    private AudioManager manager;
    private SoundPool soundPool;
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private float volume;
    private boolean loaded;
    private int soundPieceSet;
    private int soundPiecePicked;

    public OnPieceListener(Activity act, PuzzleGameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
        setAudioSettings(act);

    }

    private void setAudioSettings(Activity act) {
        manager = (AudioManager) act.getSystemService(Context.AUDIO_SERVICE);
        float currentVolumeIndex = (float) manager.getStreamVolume(streamType);
        float maxVolumeIndex = (float) manager.getStreamMaxVolume(streamType);
        volume = currentVolumeIndex * maxVolumeIndex;
        act.setVolumeControlStream(streamType);

        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        } else {
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        this.soundPieceSet = this.soundPool.load(act.getApplicationContext(), getFileResId(act, "shine"), 1);
        this.soundPiecePicked = this.soundPool.load(act.getApplicationContext(), getFileResId(act, "plop"), 1);
    }

    private int getFileResId(Activity act, String resName) {
        return act.getResources().getIdentifier(resName, "raw", act.getPackageName());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.performClick();
        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();
        final double tolerance = sqrt(pow(view.getWidth(), 2) + pow(view.getHeight(), 2)) / 10;
        Piece piece = (Piece) view;
        if (!piece.canMove()) {
            return true;
        }

        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xDelta = x - lParams.leftMargin;
                yDelta = y - lParams.topMargin;
                piece.bringToFront();
                animPieceAlpha(piece);
                playPickPieceSound();
                break;

            case MotionEvent.ACTION_MOVE:
                lParams.leftMargin = (int) (x - xDelta);
                lParams.topMargin = (int) (y - yDelta);
                view.setLayoutParams(lParams);
                break;

            case MotionEvent.ACTION_UP:
                int xDiff = abs(piece.getxCoord() - lParams.leftMargin);
                int yDiff = abs(piece.getyCoord() - lParams.topMargin);
                if (xDiff <= tolerance && yDiff <= tolerance) {
                    lParams.leftMargin = piece.getxCoord();
                    lParams.topMargin = piece.getyCoord();
                    piece.setLayoutParams(lParams);
                    piece.canMove(false);
                    sendViewToBack(piece);
                    animPieceRotate(piece);
                    playSetPieceSound();
                    gameViewModel.checkGameOver();
                }
                break;
        }
        return true;
    }

    private void playPickPieceSound() {
        if (loaded) {
            float leftVolume = volume;
            float rightVolume = volume;
            this.soundPool.play(soundPiecePicked, leftVolume, rightVolume, 1, 0, 1f);
        }
    }

    private void playSetPieceSound() {
        if (loaded) {
            float leftVolume = volume;
            float rightVolume = volume;
            this.soundPool.play(soundPieceSet, leftVolume, rightVolume, 1, 0, 1f);
        }
    }

    private void animPieceAlpha(Piece piece) {
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(piece, "alpha", 0.5f, 1f);
        animation1.setDuration(600);
        animation1.start();
    }

    private void animPieceRotate(Piece piece) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(piece, "rotation", 5, -5, 5, -5, 5, -5, 0);
        animation.setDuration(300);
        animation.start();
    }

    public void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup) child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }
}




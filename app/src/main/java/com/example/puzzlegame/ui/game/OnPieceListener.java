package com.example.puzzlegame.ui.game;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.puzzlegame.model.Piece;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class OnPieceListener implements View.OnTouchListener {

    private float xDelta;
    private float yDelta;
    private PuzzleGameViewModel gameViewModel;

    public OnPieceListener(PuzzleGameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
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
                    gameViewModel.checkGameOver();
                }
                break;
        }

        return true;
    }

    public void sendViewToBack(final View child) {
        final ViewGroup parent = (ViewGroup) child.getParent();
        if (null != parent) {
            parent.removeView(child);
            parent.addView(child, 0);
        }
    }
}




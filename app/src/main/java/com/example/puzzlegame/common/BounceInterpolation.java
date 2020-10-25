package com.example.puzzlegame.common;

public class BounceInterpolation implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    BounceInterpolation(double amplitude, double frecuency) {
        mAmplitude = amplitude;
        mFrequency = frecuency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) * Math.cos(mFrequency * time) + 1);
    }
}

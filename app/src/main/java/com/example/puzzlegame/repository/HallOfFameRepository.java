package com.example.puzzlegame.repository;

import android.app.Application;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Score;

import java.util.ArrayList;

public class HallOfFameRepository {
    private static HallOfFameRepository hallOfFameRepository;
    private AppDataBase db;
    private ArrayList<Score> scores;

    public static HallOfFameRepository getInstance(){
        if (hallOfFameRepository == null) {
            hallOfFameRepository = new HallOfFameRepository();
        }
        return hallOfFameRepository;
    }

    public void initHallOfFameRepository(Application application){
        db = Utils.getDB(application);
    }

    //from winScreen
    public void saveScore(final Score score) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.hallOfFameDAO().insertScore(score);
            }
        }).start();
    }

    public ArrayList<Score> getScores(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                 scores = (ArrayList<Score>) db.hallOfFameDAO().getAll();
            }
        }).start();
        return scores;
    }
}

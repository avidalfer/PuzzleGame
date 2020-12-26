package com.example.puzzlegame.repository;

import android.app.Activity;
import android.util.Log;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Score;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameRepository {
    private static HallOfFameRepository hallOfFameRepository;
    private AppDataBase db;
    private List<Score>scores;

    public static HallOfFameRepository getInstance(){
        if (hallOfFameRepository == null) {
            hallOfFameRepository = new HallOfFameRepository();
        }
        return hallOfFameRepository;
    }

    public void initHallOfFameRepository(Activity act){
        db = Utils.getDB(act.getApplication());
        scores = new ArrayList<>();
        try{
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    scores =  db.hallOfFameDAO().getAll();
                }
            });
            t.start();
            t.join();
        } catch (Exception e) {
            Log.d("HallOfFame", "initHallOfFameRepository: Error on getScores" + e);
        }
    }

    //from winScreen
    public void saveScore(final Score score) {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    db.hallOfFameDAO().insertScore(score);
                }
            });
            t.start();
            t.join();
        } catch (Exception e){
            Log.d("HallOfFame", "saveScore: failed" + e);
        }
    }

    public List<Score> getScores(){
        return scores;
    }
}

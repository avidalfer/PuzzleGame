package com.example.puzzlegame.ui.halloffame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameViewModel extends ViewModel {

    private final MutableLiveData<List<Score>> scores = new MutableLiveData<>();
    private final MutableLiveData<HallOfFame> hof = new MutableLiveData<>();
    private final MutableLiveData<Integer> position = new MutableLiveData<>();

    public HallOfFameViewModel() {
        updateData();
    }

    public void updateData() {
        //bringing hof from repository
//        this.scores.setValue(new ArrayList<Score>());
    }

    public LiveData<HallOfFame> getLocalHof() {
        LocalHallOfFame hof = LocalHallOfFame.getLocalHallOfFame();//get halloffame from repository
        User user = new User(1, "Ramón", Language.ES);
        Level level = new Level(1, "Fácil", 9);
        GameSession session = new GameSession(user, level);
        session.setEndTime(50000L);
        GameSession session1 = new GameSession(user, level);
        session1.setEndTime(60000L);
        GameSession session2 = new GameSession(user, level);
        session2.setEndTime(75000L);
        GameSession session3 = new GameSession(user, level);
        session3.setEndTime(95000L);
        Score score = new Score(session, "Ariadna");
        Score score1 = new Score(session1, "Gael");
        Score score2 = new Score(session2, "Mamá");
        Score score3 = new Score(session3, "Papá");
        hof.addScore(score);
        hof.addScore(score1);
        hof.addScore(score2);
        hof.addScore(score3);
        this.hof.setValue(hof);
        return this.hof;
    }

    public LiveData<List<Score>> getScores(HallOfFame hof) {
        if (hof.getClass() == LocalHallOfFame.class) {
            LocalHallOfFame localhof = (LocalHallOfFame) hof;
            scores.setValue(localhof.getLocalScores());
        }
        return scores;
    }
}

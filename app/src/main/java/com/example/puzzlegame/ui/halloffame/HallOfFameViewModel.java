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
        // call repository to get the LocalHallOfFame
        //this.hof.setValue(hof);
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

package com.example.puzzlegame.ui.halloffame;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.repository.HallOfFameRepository;

import java.util.List;

public class HallOfFameViewModel extends ViewModel {

    private MutableLiveData<List<Score>> _scores = new MutableLiveData<>();
    private HallOfFameRepository hallOfFameRepository;

    public HallOfFameViewModel() {}

    public void init(Activity act) {
        hallOfFameRepository = new HallOfFameRepository();
        hallOfFameRepository.initHallOfFameRepository(act);
    }

    public void getScores(){
        _scores.postValue(hallOfFameRepository.getScores());
    }

    public LiveData<List<Score>> getScoresObservable() {
        return _scores;
    }
}

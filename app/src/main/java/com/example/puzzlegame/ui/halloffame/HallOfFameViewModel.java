package com.example.puzzlegame.ui.halloffame;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.repository.HallOfFameRepository;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameViewModel extends ViewModel {

    private List<Score> scores = new ArrayList<>();
    private HallOfFameRepository hallOfFameRepository;

    public HallOfFameViewModel() {}

    public void init(Application application) {
        hallOfFameRepository = new HallOfFameRepository();
        hallOfFameRepository.initHallOfFameRepository(application);
        scores = hallOfFameRepository.getScores();
    }

    public List<Score> getScores() {
        return scores;
    }
}

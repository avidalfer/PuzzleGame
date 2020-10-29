package com.example.puzzlegame.ui.SelectLevel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.Level;

public class SelectLevelViewModel extends ViewModel {

    private MutableLiveData<Level> lvl;

    public SelectLevelViewModel(){
        lvl = new MutableLiveData<>();
        //lvl.setValue(userRepository.getLevel())
        Level testLevel = new Level(1, "fácil", 3, 4);
        lvl.setValue(testLevel);
    }

    public LiveData<Level> getGameLevel() {
        if (lvl == null) {
            lvl = new MutableLiveData<Level>();
        }
        return lvl;
    }

    public void setGameLevel(Level gameLevel) {
        lvl.postValue(gameLevel);
    }

    public void setGameLevelById(int lvlId) {
        //lvl.setValue(userRepository.getLevel(lvlId))
        Level testLevel = new Level(1, "fácil", 3, 4);
        lvl.postValue(testLevel);
    }
}

package com.example.puzzlegame.ui.SelectLevel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class SelectLevelViewModel extends ViewModel {

    private MutableLiveData<Integer> lvl;

    public SelectLevelViewModel(){
        lvl = new MutableLiveData<>();
        lvl.setValue(1);
    }

    public LiveData<Integer> getGameLevel() {
        return lvl;
    }

    public void setGameLevel(int gameLevel) {
        lvl.postValue(gameLevel);
    }
}

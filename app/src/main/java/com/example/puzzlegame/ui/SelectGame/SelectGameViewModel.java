package com.example.puzzlegame.ui.SelectGame;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.User;
import com.example.puzzlegame.repository.GameAppRepository;

public class SelectGameViewModel extends ViewModel {
    private final MutableLiveData<User> user;

    public SelectGameViewModel() {
        user = new MutableLiveData<User>();
    }

    public LiveData<User> getUser(Application app) {
        GameAppRepository gameAppRepository = GameAppRepository.getGameAppRepository();
        user.postValue(gameAppRepository.getCurrentUser());
        return user;
    }

}

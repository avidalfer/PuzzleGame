package com.example.puzzlegame.ui.SelectGame;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.User;
import com.example.puzzlegame.repository.GameAppRepository;

public class SelectGameViewModel extends ViewModel {
    private final MutableLiveData<User> user;

    public SelectGameViewModel() {
        user = new MutableLiveData<User>();
    }

    public void updateUserData(){
        GameAppRepository gameAppRepository = GameAppRepository.getGameAppRepository();
        user.setValue(gameAppRepository.getCurrentUser());
    }

    public MutableLiveData<User> getUserObservable(Application app) {
        return user;
    }

}

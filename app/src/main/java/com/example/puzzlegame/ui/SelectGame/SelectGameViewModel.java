package com.example.puzzlegame.ui.SelectGame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.repository.GameAppRepository;

import java.util.ArrayList;
import java.util.List;

public class SelectGameViewModel extends ViewModel {
    private final MutableLiveData<User> user;
    private List<GameSession> playingGames;

    public SelectGameViewModel() {
        user = new MutableLiveData<User>();
        playingGames = new ArrayList<>();
    }

    public LiveData<User> getUser() {
        user.postValue(GameAppRepository.getGameAppRepository().getCurrentUser());
        return user;
    }

    public List<GameSession> getUserPlayingGames() {
        ArrayList<GameSession> tempList = new ArrayList<>();
        playingGames.addAll(user.getValue().getPlayedGames());
        for (GameSession game : playingGames) {
            if (game.endTime > 0) {
                tempList.add(game);
            }
        }
        return tempList;
    }
}

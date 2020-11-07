package com.example.puzzlegame.ui.SelectPreviousGames;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;

import java.util.ArrayList;
import java.util.List;

public class SelectPreviousGamesViewModel extends ViewModel {

    private MutableLiveData<List<GameSession>> playingGames;

    public SelectPreviousGamesViewModel() {
        if (playingGames == null) {
            playingGames = new MutableLiveData<List<GameSession>>();
        }
    }

    public LiveData<List<GameSession>> getPlayingGames(User user) {
        if (user == null) return null;

        List<GameSession> tempList = new ArrayList<>();
        for (GameSession game : user.getPlayedGames()) {
            if (game.endTime == 0) {
                tempList.add(game);
            }
        }
        playingGames.postValue(tempList);
        return playingGames;
    }
}

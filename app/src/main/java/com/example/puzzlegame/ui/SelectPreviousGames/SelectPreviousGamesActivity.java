package com.example.puzzlegame.ui.SelectPreviousGames;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class SelectPreviousGamesActivity extends BaseActivity {

    SelectPreviousGamesViewModel previousGamesViewModel;
    User user;

    @Override
    protected void onCreate (Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        previousGamesViewModel = new ViewModelProvider(this).get(SelectPreviousGamesViewModel.class);
        getPlayingGames();
    }

    public LiveData<List<GameSession>> getPlayingGames(){
        return previousGamesViewModel.getPlayingGames(user);
    }
}

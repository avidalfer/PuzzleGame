package com.example.puzzlegame.ui.SelectGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;
import com.example.puzzlegame.ui.SelectLevel.SelectLevelActivity;
import com.example.puzzlegame.ui.SelectPreviousGames.SelectPreviousGamesActivity;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class SelectGameActivity extends BaseActivity {

    SelectGameViewModel selectGameViewModel;
    Button btnNewGame, btnContinueGame, btnViewPreviousGames;
    TextView userNameTxtView;
    List<GameSession> userPlayingGames;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        selectGameViewModel = new ViewModelProvider(this).get(SelectGameViewModel.class);
        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("currentUser");
        if (currentUser == null) {
           selectGameViewModel.getUser().getValue();
        }
        setViews();
        setButtonListeners();
    }

    private void setViews() {

        userNameTxtView = findViewById(R.id.userName_txt);
        btnNewGame = findViewById(R.id.btn_newGame);
        btnContinueGame = findViewById(R.id.btn_continueGame);
        btnViewPreviousGames = findViewById(R.id.btn_previousGames);
    }

    private void setButtonListeners() {
        //Buttons
        new Thread() {
            @Override
            public void run() {
                btnNewGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newGame(v);
                    }
                });
                btnContinueGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        continueGame(v);
                    }
                });
                btnViewPreviousGames.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPreviousGames(v);
                    }
                });
            }
        }.start();

        final Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                userNameTxtView.setText(user.getName());
                currentUser = user;
            }
        };
        selectGameViewModel.getUser().observe(this, userObserver);
    }
        //LiveData userPlayingGames
    private void newGame(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectLevelActivity.class);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }

    public void continueGame(View view) {
        GameSession currentGame = currentUser.getCurrentGameSession();
        Intent intent = new Intent(getApplicationContext(), PuzzleGameActivity.class);
        intent.putExtra("continueGame", currentGame);
        startActivity(intent);
    }

    public void viewPreviousGames(View view) {
        if (userPlayingGames.size() == 0) {
            Toast toast = Toast.makeText(this, this.getResources().getText(R.string.no_prev_games), Toast.LENGTH_LONG);
            toast.show();
        } else {
            Intent intent = new Intent(getApplicationContext(), SelectPreviousGamesActivity.class);
            intent.putExtra("userPlayedGames", currentUser);
            startActivity(intent);
        }
    }

    }
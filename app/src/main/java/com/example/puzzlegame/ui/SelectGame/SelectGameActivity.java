package com.example.puzzlegame.ui.SelectGame;

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
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.User;
import com.example.puzzlegame.ui.SelectLevel.SelectLevelActivity;
import com.example.puzzlegame.ui.SelectPreviousGames.SelectPreviousGamesActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.game.PuzzleGameActivity;

public class SelectGameActivity extends BaseActivity {

    SelectGameViewModel selectGameViewModel;
    Button btnNewGame, btnContinueGame, btnViewPreviousGames;
    TextView userNameTxtView;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        selectGameViewModel = new ViewModelProvider(this).get(SelectGameViewModel.class);

        /* Lógica de usuario
        Pendiente de saber cómo afecta el login en la lógica de la aplicación.
        Por ahora si no existe usuario (Producto 1) se genera un usario por defecto
         */
        if (currentUser == null) {
            selectGameViewModel.updateUserData();
        }
        // Fin lógica usuario

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

        final Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null){
                userNameTxtView.setText(user.getName() + "!");
                currentUser = user;
                }
            }
        };
        selectGameViewModel.getUserObservable(getApplication()).observe(this, userObserver);

    }
        //LiveData userPlayingGames
    private void newGame(View view) {
        startActivity(new Intent(getApplicationContext(), SelectLevelActivity.class));
    }

    public void continueGame(View view) { //pendiente de revisión. Chequear qué valores tiene el user recibido desde BD
        GameSession currentGame = currentUser.getCurrentGameSession();
        Intent intent = new Intent(getApplicationContext(), PuzzleGameActivity.class);
        intent.putExtra("continueGame", currentGame);
        startActivity(intent);
    }

    public void viewPreviousGames(View view) {
        if (currentUser.getPlayedGames().size() == 0) {
            Toast.makeText(this, this.getResources().getText(R.string.no_prev_games), Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), SelectPreviousGamesActivity.class);
            intent.putExtra("userPlayedGames", currentUser);
            startActivity(intent);
        }
    }

    }
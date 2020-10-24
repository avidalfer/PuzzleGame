package com.example.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

import static android.R.color.white;

public class SelectGameActivity extends BaseActivity {

    Button btnNewGame, btnContinueGame, btnViewPreviousGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

//        Muestra el nombre de usuario o mapache
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String receivedName = intent, getStringExtra (LoggingActivity.EXTRA_MESSAGE);
//
//            TextView userNameTxt = findViewById(R.id.userName_txt);
//            userNameTxt.setText(receivedName);
//        }
//
        setButtonListeners();
    }

    private void setButtonListeners() {

        btnNewGame = findViewById(R.id.btn_newGame);
        btnContinueGame = findViewById(R.id.btn_continueGame);
        btnViewPreviousGames = findViewById(R.id.btn_previousGames);

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

    private void newGame(View view) {
        Intent intent = new Intent(view.getContext(), SelectLevelActivity.class);
        startActivity(intent);
    }

    public void continueGame(View view) {
        // llamada a repositorio: último GameSession de la lista de jugados por el jugador
        // o
        // llamada a actividad nueva que cargue el juego guardado
        Utils.TODO(this, view);
    }

    public void viewPreviousGames(View view) {
        // llamada a repositorio: lista de GameSessions.
        // o
        // llamada a actividad nueva que cargue el juego guardado
        Utils.TODO(this, view);
    }

}
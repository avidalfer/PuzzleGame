package com.example.puzzlegame.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.SelectLevel.SelectLevelActivity;
import com.example.puzzlegame.ui.common.BaseActivity;

public class SelectGameActivity extends BaseActivity {

    Button btnNewGame, btnContinueGame, btnViewPreviousGames;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

//        Muestra el nombre de usuario o mapache
//          Posibles implementaciones:  MVVM con acceso a la base de datos y muestra el nombre de usuario
//                                      Recibe un Intent con el texto del usuario, recuperado del caso de uso login o signup

        setViews();
        setButtonListeners();
    }

    private void setViews() {
        userName = findViewById(R.id.userName_txt);
        btnNewGame = findViewById(R.id.btn_newGame);
        btnContinueGame = findViewById(R.id.btn_continueGame);
        btnViewPreviousGames = findViewById(R.id.btn_previousGames);
    }

    private void setButtonListeners() {
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
    }

    private void newGame(View view) {
        startActivity(new Intent(getApplicationContext(), SelectLevelActivity.class));
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
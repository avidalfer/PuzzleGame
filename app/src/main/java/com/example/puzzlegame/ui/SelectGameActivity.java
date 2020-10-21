package com.example.puzzlegame.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;

public class SelectGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);

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
    }

    public void newGame(View view) {
        //Intent intent = new Intent(this, SelectLevelGame.class);
        //startActivity(intent);
        Utils.TODO(this, view);
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
package com.example.puzzlegame.ui.halloffame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

public class HallOfFameActivity extends BaseActivity {

    private Button btnNewGame;
    private ImageButton changeHoF;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hof);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setViews();
    }

    private void setViews() {

        btnNewGame = findViewById(R.id.btn_newGame);
        listView = (ListView) findViewById(R.id.list_scores);
    }
}

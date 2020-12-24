package com.example.puzzlegame.ui.halloffame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.ui.selectLevel.SelectLevelActivity;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class HallOfFameActivity extends BaseActivity {

    private HallOfFameViewModel hallOfFameViewModel;
    private ImageButton changeHoF;

    private Score score;

    private RecyclerView winnersListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hof);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);
        init();
        setViews();
        hallOfFameViewModel.getScores();
    }

    private void init() {
        hallOfFameViewModel = new ViewModelProvider(this).get(HallOfFameViewModel.class);
        hallOfFameViewModel.init(getApplication());
    }

    private void setViews() {
        Button btnNewGame = findViewById(R.id.btn_newGame);
        winnersListView = findViewById(R.id.winners_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        winnersListView.setLayoutManager(layoutManager);

        final Observer<List<Score>> scoreObserver = new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> scores) {
                RecyclerView.Adapter<HallOfFameAdapter.MyViewHolder> adapter = new HallOfFameAdapter(scores);
                winnersListView.setAdapter(adapter);
            }
        };
        hallOfFameViewModel.getScoresObservable().observe(this, scoreObserver);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelectLevelActivity.class));
            }
        });
    }
}

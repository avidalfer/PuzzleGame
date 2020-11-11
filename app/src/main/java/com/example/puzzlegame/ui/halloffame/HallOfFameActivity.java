package com.example.puzzlegame.ui.halloffame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class HallOfFameActivity extends BaseActivity {

    private HallOfFameViewModel hallOfFameViewModel;
    private Button btnNewGame;
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
    }

    private void init() {
        hallOfFameViewModel = new ViewModelProvider(this).get(HallOfFameViewModel.class);
        hallOfFameViewModel.init(getApplication());
    }

    private void setViews() {
        btnNewGame = findViewById(R.id.btn_newGame);
        winnersListView = findViewById(R.id.winners_list);
        winnersListView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        winnersListView.setLayoutManager(layoutManager);

        showLocalHallOfFame();
    }

    private void showLocalHallOfFame() {
        List<Score> scores = hallOfFameViewModel.getScores();
        RecyclerView.Adapter<HallOfFameAdapter.MyViewHolder> adapter = new HallOfFameAdapter(scores);
        winnersListView.setAdapter(adapter);
    }
}

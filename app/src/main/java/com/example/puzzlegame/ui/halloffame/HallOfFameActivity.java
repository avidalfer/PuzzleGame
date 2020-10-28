package com.example.puzzlegame.ui.halloffame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.model.interfaces.HallOfFame;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

public class HallOfFameActivity extends BaseActivity {

    private HallOfFameViewModel hallOfFameViewModel;
    private Button btnNewGame;
    private ImageButton changeHoF;

    private HallOfFame hof;
    private List<Score> scores;

    private RecyclerView winnersListView;
    private RecyclerView.Adapter<HallOfFameAdapter.MyViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hof);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setViews();
    }

    private void setViews() {

        hallOfFameViewModel = new HallOfFameViewModel();
        btnNewGame = findViewById(R.id.btn_newGame);
        winnersListView = findViewById(R.id.winners_list);
        winnersListView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        winnersListView.setLayoutManager(layoutManager);

        showLocalHallOfFame();
    }

    private void showLocalHallOfFame() {
        hof = (LocalHallOfFame) hallOfFameViewModel.getLocalHof().getValue();
        scores = hallOfFameViewModel.getScores(hof).getValue();
        adapter = new HallOfFameAdapter(this, hof, scores);
        winnersListView.setAdapter(adapter);
    }

    private LiveData<HallOfFame> getLocalHof(){
        return hallOfFameViewModel.getLocalHof();
    }
}

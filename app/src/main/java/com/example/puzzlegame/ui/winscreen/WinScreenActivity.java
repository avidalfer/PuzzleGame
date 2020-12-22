package com.example.puzzlegame.ui.winscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

<<<<<<< Updated upstream
=======
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
>>>>>>> Stashed changes
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;

public class WinScreenActivity extends BaseActivity {

    private WinScreenViewModel winScreenViewModel;
    private EditText winnerNameTxt;
    private long winTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        Intent intent = getIntent();
        winTime = intent.getExtras().getLong("winTime");

        //check if has arrived from game finished properly
        if (winTime == 0) {
            finish();
        }
        init();
        setViews();
        setListeners();
    }

    private void init() {
        winScreenViewModel = new ViewModelProvider(this).get(WinScreenViewModel.class);
        winScreenViewModel.initRepo(getApplication());
    }

    private void setViews() {

        TextView winTimeTxt = findViewById(R.id.time);
        winTimeTxt.setText(winScreenViewModel.getFormattedTime(winTime));

        winnerNameTxt = findViewById(R.id.win_editText_name);
        winnerNameTxt.setText(winScreenViewModel.getUserName());
    }

    private void setListeners() {
        Button btnToHOF = findViewById(R.id.btn_toHoF);

        btnToHOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String winnerName = winnerNameTxt.getText().toString();
                Score score = new Score(winnerName, winTime);
                if (winScreenViewModel.isRecord(score)) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),getString(R.string.idcanal) )
                            .setSmallIcon(R.drawable.copa_icon)
                            .setContentTitle(getString(R.string.record))
                            .setContentText(getString(R.string.notiftxt))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                };
                winScreenViewModel.saveScore(score);
                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });

    }
}
package com.example.puzzlegame.ui.winscreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;

import java.util.Calendar;
import java.util.Date;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addScoreToCalendar(winTime);
        }

        //check if has arrived from game finished properly
        if (winTime == 0) {
            finish();
        }
        init();
        setViews();
        setListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addScoreToCalendar(long winTime) {
        Date beginTime = Calendar.getInstance().getTime();
        Date endTime = Calendar.getInstance().getTime();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, Calendar.getInstance().getTime())
                .putExtra(CalendarContract.Events.TITLE, "New Score" + winTime)
                .putExtra(CalendarContract.Events.DESCRIPTION, "Score String")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Mapuzzled");
        startActivity(intent);
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
                winScreenViewModel.saveScore(score);

                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });
    }
}
package com.example.puzzlegame.ui.winscreen;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;

import java.util.Calendar;
import java.util.Date;

public class WinScreenActivity extends BaseActivity {

    private static final String CHANNEL_ID = "Record";
    private static final String CHANNEL_NAME = "Record Name";
    private static final String CHANNEL_DESC = "Record Description";
    private WinScreenViewModel winScreenViewModel;
    private EditText winnerNameTxt;
    private long winTime;
    private boolean scoreSaved = false;

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
        setNotificationsChannel();
    }

    private void setNotificationsChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addScoreToCalendar(long winTime) {
        Date beginTime = Calendar.getInstance().getTime();
        Date endTime = Calendar.getInstance().getTime();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                .putExtra(CalendarContract.Events.TITLE, getString(R.string.puzzle_solved) + Utils.FormatTime(winTime))
                .putExtra(CalendarContract.Events.DESCRIPTION, getString(R.string.record))
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Mapuzzled");
        startActivity(intent);
    }

    private void init() {
        winScreenViewModel = new ViewModelProvider(this).get(WinScreenViewModel.class);
        winScreenViewModel.initRepo(this);
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
                scoreSaved = true;
                String winnerName = winnerNameTxt.getText().toString();
                Score score = new Score(winnerName, winTime);
                if (winScreenViewModel.isRecord(score)) {
                    DisplayNotification();
                }
                winScreenViewModel.saveScore(score);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    addScoreToCalendar(winTime);
                }
            }
        });
    }

    private void DisplayNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.copa_icon)
                .setContentTitle(getString(R.string.congrats))
                .setContentText(getString(R.string.record) + Utils.FormatTime(winTime))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (scoreSaved) {
            startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
        }
    }
}
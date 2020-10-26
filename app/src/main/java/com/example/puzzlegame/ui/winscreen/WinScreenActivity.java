package com.example.puzzlegame.ui.winscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;

public class WinScreenActivity extends BaseActivity {

    private WinScreenViewModel winScreenViewModel;
    private EditText winnerNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setViews();
        setListeners();
    }

    private void setViews() {
        winScreenViewModel = new ViewModelProvider(this).get(WinScreenViewModel.class);

        TextView winTimeTxt = findViewById(R.id.time);
        winTimeTxt.setText(getWinnerTime());

        winnerNameTxt = findViewById(R.id.win_editText_name);
        winnerNameTxt.setText(getUserName());
        }

    private String getUserName() {
        return winScreenViewModel.getUserName();
    }

    private String getWinnerTime() {
        return winScreenViewModel.getFormattedTime();
    }

    private void setListeners() {
        Button btnToHOF = findViewById(R.id.btn_toHoF);

        btnToHOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String winnerName = winnerNameTxt.getText().toString();
                winScreenViewModel.setWinnerName(winnerName);
                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });
    }
}
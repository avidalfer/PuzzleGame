package com.example.puzzlegame.ui;

import android.os.Bundle;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

public class HallOfFameActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hof);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);
    }
}

package com.example.puzzlegame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = Utils.createToolbar(this);
        ActionBar actionBar = Utils.configDefaultAppBar(this);
    }
}
package com.example.puzzlegame.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class SelectLevelActivity extends BaseActivity {

    private Switch[] switches;
    private Integer level;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        final ViewGroup levelChoseLayout = findViewById(R.id.levelChooseLayout);

        final Switch easySW = levelChoseLayout.findViewById(R.id.easy_switch);
        final Switch mediumSW = levelChoseLayout.findViewById(R.id.medium_switch);
        final Switch hardSW = levelChoseLayout.findViewById(R.id.hard_switch);

        switches = new Switch[] { easySW, mediumSW, hardSW };
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void setLevelGame (View view) {

        Switch sw = (Switch) view;
        setAllSwitchesOff();

        switch (sw.getId()){
            case R.id.easy_switch:
                level = 1;
                break;
            case R.id.medium_switch:
                level = 2;
                break;
            case R.id.hard_switch:
                level = 3;
                break;
        }
        sw.setChecked(true);
    }

    private void setAllSwitchesOff() {
        for (Switch sw : switches) {
            sw.setChecked(false);
        }
    }

    public void goToImageSelectionActivity(View view) {
        Utils.TODO(this,view);

        //enviamos con un intent la dificultad seleccionada para crear la partida más tarde

        //Intent intent = new Intent(this, ImageSelectionActivity.class);
        //intent.putExtra("levelInt", level);
        //startActivity(intent);
    }
}
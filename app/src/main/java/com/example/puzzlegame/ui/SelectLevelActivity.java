package com.example.puzzlegame.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.models.User;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class SelectLevelActivity extends BaseActivity {

    private Switch[] switches;
    private int userlvl;
    private int selectedLvl;
    private User user;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        userlvl = 3;//user.getUserLvl().getId();

        final ViewGroup levelChoseLayout = findViewById(R.id.levelChooseLayout);

        final Switch easySW = levelChoseLayout.findViewById(R.id.easy_switch);
        final Switch mediumSW = levelChoseLayout.findViewById(R.id.medium_switch);
        final Switch hardSW = levelChoseLayout.findViewById(R.id.hard_switch);

        switches = new Switch[] { easySW, mediumSW, hardSW };

        allowsAsUserLevel(switches);
    }

    /**
     * Set enabled switches to match user level
     * case 1 is always enabled. Don't use break to get cascade behaviour
     * @param switches
     */
    private void allowsAsUserLevel(Switch[] switches) {

        switch (userlvl) {
            case 3: switches[2].setEnabled(true);
            case 2: switches[1].setEnabled(true);
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void setLevelGame (View view) {

        Switch sw = (Switch) view;
        setAllSwitchesOn(false);

        switch (sw.getId()){
            case R.id.easy_switch:
                selectedLvl = 1;
                break;
            case R.id.medium_switch:
                selectedLvl = 2;
                break;
            case R.id.hard_switch:
                selectedLvl = 3;
                break;
        }
        sw.setChecked(true);
    }

    private void setAllSwitchesOn(boolean in) {
        for (Switch sw : switches) {
            sw.setChecked(in);
        }
    }

    public void goToImageSelectionActivity(View view) {
        Utils.TODO(this,view);

        //enviamos con un intent la dificultad seleccionada para crear la partida más tarde

        //Intent intent = new Intent(this, ImageSelectionActivity.class);
        //intent.putExtra("levelInt", selectedLevel);
        //startActivity(intent);
    }
}
package com.example.puzzlegame.ui;


import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.models.User;
import com.example.puzzlegame.ui.common.BaseActivity;

public class SelectLevelActivity extends BaseActivity {

    private CompoundButton[] switches;
    private int userlvl;
    private int lvl;
    private int selectedLvl;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        userlvl = 3;//user.getUserLvl().getId();

        final ViewGroup levelChoseLayout = findViewById(R.id.levelChooseLayout);

        final CompoundButton easySW = levelChoseLayout.findViewById(R.id.easy_switch);
        final CompoundButton mediumSW = levelChoseLayout.findViewById(R.id.medium_switch);
        final CompoundButton hardSW = levelChoseLayout.findViewById(R.id.hard_switch);

        switches = new CompoundButton[] { easySW, mediumSW, hardSW };

        addSwitchListeners(switches);
        allowsAsUserLevel(switches);
    }

    private void addSwitchListeners(final CompoundButton[] switches) {

        for (CompoundButton sw : switches) {
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        setLevelGame(buttonView);
                    }
                }
            });
        }
    }

    /**
     * Set enabled switches to match user level
     * case 1 is always enabled. Don't use break to get cascade behaviour
     * @param switches
     */
    private void allowsAsUserLevel(CompoundButton[] switches) {

        switch (userlvl) {
            case 3: switches[2].setEnabled(true);
            case 2: switches[1].setEnabled(true);
        }
    }

    private void setLevelGame (CompoundButton btnSelected) {

        switch (btnSelected.getId()){
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
        setRestLevelsOff(btnSelected);
    }

    private void setRestLevelsOff(CompoundButton btnSelected) {
        for (CompoundButton sw : switches) {
            if (!sw.equals(btnSelected)) {
                sw.setChecked(false);
            }
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
package com.example.puzzlegame.ui.SelectLevel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.puzzlegame.R;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.Objects;

public class SelectLevelActivity extends BaseActivity {

    private SelectLevelViewModel levelViewModel;

    private CompoundButton easySW, mediumSW, hardSW;
    private CompoundButton[] switches;
    private Button btnPlay;

    private int userlvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Utils.createToolbar(this);
        Utils.configDefaultAppBar(this);

        setViews();
        setListeners();
        getLastLevelPlayed();
        allowsAsUserLevel();
    }


    private void setViews() {
        levelViewModel = new ViewModelProvider(this).get(SelectLevelViewModel.class);
        easySW = findViewById(R.id.easy_switch);
        mediumSW = findViewById(R.id.medium_switch);
        hardSW = findViewById(R.id.hard_switch);
        btnPlay = findViewById(R.id.btn_play);

        switches = new CompoundButton[]{easySW, mediumSW, hardSW};
    }

    private void setListeners() {
        new Thread() {
            @Override
            public void run() {
                //Switches
                for (CompoundButton sw : switches) {
                    sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                setLvl(buttonView);
                            }
                        }
                    });
                }
                //Button
                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToImageSelectionActivity();
                    }
                });
            }
        }.start();
        //LiveData
        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onChanged(Integer lvl) {
                levelViewModel.setGameLevel(lvl);
            }
        };
        levelViewModel.getGameLevel().observe(this, observer);
    }

    /**
     * Set enabled switches to match user level
     * case 1 is always enabled. Don't use break to get cascade behaviour
     */
    private void allowsAsUserLevel() {
        TextView levelTxt;

        switch (userlvl) {
            case 3:
                switches[2].setEnabled(true);
                levelTxt = findViewById(R.id.hard_txt);
                levelTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            case 2:
                switches[1].setEnabled(true);
                levelTxt = findViewById(R.id.medium_txt);
                levelTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    private void getLastLevelPlayed() {
        userlvl = 3;
        int lastGameLevel = 1;
        //userlvl = user.playedgames.last
        //if (userlvl != null)
        //lvl = userlvl
        //levelViewModel.setGameLevel(lvl);
        setLvl(lastGameLevel);
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    private void setLvl(CompoundButton switchBtn) {
        int lvl = 1;
        String levelTitle = "";
        switch (switchBtn.getId()) {
            case R.id.easy_switch:
                btnPlay.setText(getText(R.string.easy_btn));
                btnPlay.setBackground(getDrawable(R.drawable.btn_purple_states));
                lvl = 1;
                break;
            case R.id.medium_switch:
                btnPlay.setText(getText(R.string.medium_btn));
                btnPlay.setBackground(getDrawable(R.drawable.btn_orange_states));
                lvl = 2;
                break;
            case R.id.hard_switch:
                btnPlay.setText(getText(R.string.hard_btn));
                btnPlay.setBackground(getDrawable(R.drawable.btn_red_states));
                lvl = 3;
                break;
        }

        setRestLevelsOff(switchBtn);
        levelViewModel.setGameLevel(lvl);
    }

    private void setLvl(int levelId) {
        switch (levelId) {
            case 1:
                setRestLevelsOff(easySW);
                break;
            case 2:
                setRestLevelsOff(mediumSW);
                break;
            case 3:
                setRestLevelsOff(hardSW);
                break;
        }
    }

    private void setRestLevelsOff(CompoundButton btnSelected) {
        for (CompoundButton sw : switches) {
            sw.setChecked(sw.equals(btnSelected));
        }
    }

        private void goToImageSelectionActivity () {
            String debug;
            debug = Objects.requireNonNull(levelViewModel.getGameLevel().getValue()).toString();
            Toast t = Toast.makeText(getApplicationContext(), debug, Toast.LENGTH_SHORT);
            t.show();

            //Posibles implementaciones
            //Intent intent = new Intent(this, ImageSelectionActivity.class);
            //intent.putExtra("levelInt", selectedLevel);
            //startActivity(intent);

            //pasamos el nivel a la capa de repositorio donde se crea una gameSession y se accede desde todas las activities a la misma sesión para añadir más configuraciones.
            //se eliminará la sesión si no se ha llegado a iniciar.
        }
    }
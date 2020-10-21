package com.example.puzzlegame.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlegame.R;

public class SelectLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
    }

    public void newGame() {
        //Intent intent = new Intent(this, SelectLevelGame.class);
        //startActivity(intent);
    }

    public void TODO(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Function not implemented yet", Toast.LENGTH_SHORT);
        toast.show();
    }
}
package com.example.puzzlegame.ui.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzlegame.R;

public class BaseActivity extends AppCompatActivity {

    /**
     * create menu from app menu resource
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Contains the logic from menu items clicked.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Toast toast;

        switch (item.getItemId()) {
            case android.R.id.home:
                //call settingsView fragment
                toast = Toast.makeText(getApplicationContext(), "Calling to SettingsView Fragment", Toast.LENGTH_SHORT); //provisional
                toast.show();
                return true;
            case R.id.action_help:
                //call helpView fragment
                toast = Toast.makeText(getApplicationContext(), "Calling to HelpView Fragment", Toast.LENGTH_SHORT); //provisional
                toast.show();
                return true;
            default:
                toast = Toast.makeText(getApplicationContext(), "Action not implemented", Toast.LENGTH_SHORT);
                toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
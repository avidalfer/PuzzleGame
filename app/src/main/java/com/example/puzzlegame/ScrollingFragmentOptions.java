package com.example.puzzlegame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ScrollingFragmentOptions extends Fragment {

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5,
            radioButton6, radioButton7, radioButton8, radioButton9;
    private Object SelectGameActivity;


    public ScrollingFragmentOptions(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling_options, container, false);
    }

   /* public void SeleccionMelodia (View view) {
        switch (view.getId()) {
            case R.id.radioButton1:
                Toast.makeText(this, "Mapuzzled Official Theme", Toast.LENGTH_SHORT.show());
                break;
            case R.id.radioButton2:
                Toast.makeText(this, "Cargar canción propia", Toast.LENGTH_SHORT.show());
                break;
        }
    }
    public void SeleccionCancion (View view) {
        switch (view.getId()) {
            case R.id.radioButton3:
                Toast.makeText(this, "Canción guardada 1", Toast.LENGTH_SHORT.show());
                break;
            case R.id.radioButton4:
                Toast.makeText(this, "Canción guardada 2", Toast.LENGTH_SHORT.show());
                break;
            case R.id.radioButton5:
                Toast.makeText(this, "Canción guardada 3", Toast.LENGTH_SHORT.show());
                break;
        }
    }
*/
    public void SeleccionReiniciar (View view) {
        if (radioButton6.isChecked() == true) {
            Intent intent = new Intent();
            Context actividad = null;
            intent.setClass(actividad, actividad.getClass());
            //llamamos a la actividad
            actividad.startActivity(intent);
            //finalizamos la actividad actual

        }else radioButton7.isChecked();

    }

    private Context getBaseContext() {
        return null;
    }

    public void SeleccionSalir (View view){
        if(radioButton8.isChecked() == true) {
            Intent intent =new Intent (Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else  radioButton9.isChecked();

    }
}
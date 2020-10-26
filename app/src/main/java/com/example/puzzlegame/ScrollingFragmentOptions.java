package com.example.puzzlegame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class ScrollingFragmentOptions extends Fragment {

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5,
            radioButton6, radioButton7, radioButton8, radioButton9;

    ScrollingFragmentOptions(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, RadioButton radioButton7, RadioButton radioButton8, RadioButton radioButton9) {
        this.radioButton1 = radioButton1;
        this.radioButton2 = radioButton2;
        this.radioButton3 = radioButton3;
        this.radioButton4 = radioButton4;
        this.radioButton5 = radioButton5;
        this.radioButton6 = radioButton6;
        this.radioButton7 = radioButton7;
        this.radioButton8 = radioButton8;
        this.radioButton9 = radioButton9;
    }

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
    public void SeleccionReiniciar (View view){
        if (radioButton6.isChecked() == true) MainActivity.onRestart();
           /*Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);*/
        else if (radioButton7.isChecked() == true){

        }
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
        }else if  (radioButton9.isChecked() == true) {
           
        }

    }
}
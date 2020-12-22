package com.example.puzzlegame;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.RequiresApi;

import com.example.puzzlegame.common.CommonBarMethods;
import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameAppRepository;
import com.example.puzzlegame.repository.HallOfFameRepository;
import com.example.puzzlegame.ui.SelectGame.SelectGameActivity;
import com.example.puzzlegame.ui.common.BaseActivity;
import com.example.puzzlegame.ui.halloffame.HallOfFameActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends BaseActivity {

    Button btn1, btn2;
    Button[] buttons;
    GameAppRepository gameAppRepository;
    GalleryRepository galleryRepository;
    HallOfFameRepository hallOfFameRepository;
    NotificationChannel canal=new NotificationChannel(getString(R.string.idcanal),getString(R.string.canalname), NotificationManager.IMPORTANCE_DEFAULT);

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canalname);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.idcanal),getString(R.string.canalname), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonBarMethods.createToolbar(this);
        CommonBarMethods.configDefaultAppBar(this);

        init();
        setViews();
        setListeners();

    }

    /**
     * getDataBase instance for the first time and set all levels if they are not set
     * run on background
     */
    private void init() {
                galleryRepository = GalleryRepository.initGalleryRepository(getApplication());
                gameAppRepository = GameAppRepository.initGameAppRepository(getApplication());
                hallOfFameRepository = HallOfFameRepository.getInstance();
                gameAppRepository.getCurrentUser(); // pendiente de actualización con la implementación del login
                hallOfFameRepository.initHallOfFameRepository(getApplication());
    }

    private void setViews() {
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
    }

    private void setListeners() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SelectGameActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HallOfFameActivity.class));
            }
        });

    }
}

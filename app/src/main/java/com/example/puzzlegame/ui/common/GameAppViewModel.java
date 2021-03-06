package com.example.puzzlegame.ui.common;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.repository.GalleryRepository;
import com.example.puzzlegame.repository.GameAppRepository;
import com.example.puzzlegame.repository.HallOfFameRepository;

public class GameAppViewModel extends ViewModel {
    GameAppRepository gameAppRepository;
    GalleryRepository galleryRepository;
    HallOfFameRepository hallOfFameRepository;

    public void begin(Activity act) {
        galleryRepository = GalleryRepository.initGalleryRepository(act);
        gameAppRepository = GameAppRepository.initGameAppRepository(act);
        hallOfFameRepository = HallOfFameRepository.getInstance();
        gameAppRepository.getCurrentUser(); // pendiente de actualización con la implementación del login
        hallOfFameRepository.initHallOfFameRepository(act);
    }
}

package com.example.puzzlegame.repository;

import android.app.Application;
import android.content.res.AssetManager;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.User;

import java.util.List;

public class GameAppRepository {


    private static GameAppRepository gameAppRepository;
    private User currentUser;
    private AppDataBase db;
    private List<Level> levels;

    private GameAppRepository(Application application) {
        db = Utils.getDB(application);
        updateLevels();
        updateGallery(application);
    }

    private void updateGallery(Application app) {
        AssetManager am = app.getAssets();
        GalleryRepository galleryRepository = GalleryRepository.getGalleryRepository();
        galleryRepository.updateImageList(am, false);
    }

    public static GameAppRepository initGameAppRepository(Application application) {
        if (gameAppRepository == null) {
            gameAppRepository = new GameAppRepository(application);
        }
        return gameAppRepository;
    }

    public static GameAppRepository getGameAppRepository() {
        return gameAppRepository;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                currentUser = db.gameAppDAO().getCurrentUser();
                if (currentUser == null) {
                    currentUser = new User(1, "Mapache", Language.ES);
                    setCurrentUser(currentUser);
                }
            }
        }).start();
        return currentUser;
        }

        public void updateLevels () {
            levels = db.levelDAO().getAll();
            if (levels.size() == 0) {
                Level easy = new Level(1, "Easy", 3, 4);
                Level intermediate = new Level(2, "Intermediate", 4, 6);
                Level hard = new Level(3, "Hard", 6, 10);
                db.levelDAO().insertLevels(easy, intermediate, hard);
            }
        }

        /**
         * Posible implementación de guardado del usuario desde el login
         *
         * @param user
         */
        public void setCurrentUser (User user){
            if (db.userDAO().findByName(user.getName()) == null) {
                db.userDAO().insertAll(user);
            }
            db.gameAppDAO().setCurrentUser(user);
        }

        public List<Level> getLevels () {
            return levels;
        }
    }

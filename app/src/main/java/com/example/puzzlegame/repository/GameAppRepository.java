package com.example.puzzlegame.repository;

import android.app.Application;
import android.content.res.AssetManager;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Manage callings concerning users and levels
 * + link to galleryRepository to update fileData for the first time
 */
public class GameAppRepository {

    private static GameAppRepository gameAppRepository;
    private User currentUser;
    private AppDataBase db;
    private List<Level> levels;

    private GameAppRepository(Application application) {
        db = Utils.getDB(application);
        initGameAppData();
        updateLevels();
        updateGallery(application);
    }

    private void initGameAppData() {
        if (db.gameAppDAO().getGameAppData() == null) {
            db.gameAppDAO().setGameAppData(new GameApp());
        }
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
                    currentUser = new User(1, "Racoon", Language.ES);
                    setCurrentUser(currentUser);
                }
            }
        }).start();
        return currentUser;
    }

    public void updateLevels() {
        levels = db.levelDAO().getAll();
        if (levels.size() == 0) {
            Level easy = new Level(1, "Easy", 3, 4);
            Level intermediate = new Level(2, "Intermediate", 4, 6);
            Level hard = new Level(3, "Hard", 6, 10);
            levels.addAll(Arrays.asList(easy, intermediate, hard));
            db.levelDAO().insertLevels(easy, intermediate, hard);
        }
    }

    /**
     * Posible implementación de guardado del usuario desde el login
     *
     * @param user
     */
    public void setCurrentUser(User user) {
        if (db.userDAO().findByName(user.getName()) == null) {
            db.userDAO().insertUser(user);
        }
        db.gameAppDAO().setCurrentUser(user);
        currentUser = user;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public User setUserLevel(int i) {
        currentUser.setUserLvl(levels.get(i));
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.userDAO().updateUser(currentUser);
            }
        }).start();
        return currentUser;
    }
}

package com.example.puzzlegame.repository;

import android.app.Activity;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.LocalHallOfFame;
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
    private LocalHallOfFame hof;

    private GameAppRepository(Activity act) {
        db = Utils.getDB(act.getApplication());
        initGameAppData();
        updateLevels();
        updateGallery(act);
    }

    private void initGameAppData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db.gameAppDAO().getGameAppData() == null) {
                    db.gameAppDAO().setGameAppData(new GameApp());
                }
            }
        }).start();
    }

    private void updateGallery(Activity act) {
        GalleryRepository galleryRepository = GalleryRepository.getGalleryRepository();
        galleryRepository.updateImageList(act, false);
    }

    public static GameAppRepository initGameAppRepository(Activity act) {
        if (gameAppRepository == null) {
            gameAppRepository = new GameAppRepository(act);
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
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    currentUser = db.gameAppDAO().getCurrentUser();
                    if (currentUser == null) {
                        currentUser = new User(1, "Racoon", Language.ES);
                        setCurrentUser(currentUser);
                    }
                        Level lvl = db.levelDAO().getLevel(currentUser.userLvlId);
                        if (lvl == null) {
                            lvl = new Level(1, "Easy", 3, 4);
                        }
                    currentUser.setUserLvl(lvl);
                }
            });
            t.start();
            t.join();
        } catch (Exception e) {
            currentUser = new User(1, "Racoon", Language.ES);
        }
        return currentUser;
    }

    public void updateLevels() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                levels = db.levelDAO().getAll();
                if (levels.size() == 0) {
                    Level easy = new Level(0, "Easy", 3, 4);
                    Level intermediate = new Level(1, "Intermediate", 4, 6);
                    Level hard = new Level(2, "Hard", 6, 10);
                    levels.addAll(Arrays.asList(easy, intermediate, hard));
                    db.levelDAO().insertLevels(easy, intermediate, hard);
                }
            }
        }).start();
    }

    /**
     * Posible implementación de guardado del usuario desde el login
     *
     * @param user
     */
    public void setCurrentUser(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db.userDAO().findByName(user.getName()) == null) {
                    db.userDAO().insertUser(user);
                }
                db.gameAppDAO().setCurrentUser(user);
                currentUser = user;
            }
        }).start();
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

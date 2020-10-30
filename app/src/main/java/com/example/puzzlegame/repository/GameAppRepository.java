package com.example.puzzlegame.repository;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.GameApp;
import com.example.puzzlegame.model.GameSession;
import com.example.puzzlegame.model.Image;
import com.example.puzzlegame.model.Language;
import com.example.puzzlegame.model.Level;
import com.example.puzzlegame.model.MusicSettings;
import com.example.puzzlegame.model.Song;
import com.example.puzzlegame.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameAppRepository {

    private static GameAppRepository gameAppRepository;
    private GameApp gameApp;
    private User currentUser;
    private MusicSettings musicSettings;
    private Song selectedSong;
    private List<User> listOfUsers;

    private GameAppRepository() {
        gameApp = GameApp.getGameApp();
        setLevels();
    }

    public static GameAppRepository getGameAppRepository() {
        if (gameAppRepository == null) {
            gameAppRepository = new GameAppRepository();
        }
        return gameAppRepository;
    }

    public User getCurrentUser() {

        if (currentUser == null){
            currentUser = new User(1, "Mapache", Language.ES);
        }
        return currentUser;
    }

    public void setLevels(){
        //Habrá que controlar que exista en la base de datos o crearlo
        Level easy = new Level (1, "Easy", 3, 4);
        Level intermediate = new Level (2, "Intermediate", 4, 6);
        Level hard = new Level (3, "Hard", 6, 10);
        Level[] tempLevels = new Level[] {easy, intermediate, hard};
        List<Level> defaultLevels = new ArrayList<>();
        defaultLevels.addAll(Arrays.asList(tempLevels));
        gameApp.setLevels(defaultLevels);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public List<Image> getGallery(Context context) {
        GalleryRepository gallery = GalleryRepository.getGalleryRepository();
        return gallery.getImageList(context);
    }
}

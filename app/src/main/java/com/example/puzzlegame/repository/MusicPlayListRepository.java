package com.example.puzzlegame.repository;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.example.puzzlegame.basededatos.AppDataBase;
import com.example.puzzlegame.common.Utils;
import com.example.puzzlegame.model.MusicSettings;
import com.example.puzzlegame.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayListRepository {
    private static MusicPlayListRepository Instance;
    private AppDataBase db;
    private List<Song> playList;
    private int volume;
    private boolean musicState;
    private MusicSettings settings;

    private MusicPlayListRepository() {
    }

    public static MusicPlayListRepository getInstance() {
        if (Instance == null) {
            Instance = new MusicPlayListRepository();
        }
        return Instance;
    }

    public void initMusicPlayListRepository(Activity act) {
        db = Utils.getDB(act.getApplication());
        playList = new ArrayList<>();
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    settings = db.gameAppDAO().getSettings();
                    if (settings == null) db.gameAppDAO().setDefaultSettings();
                    playList = db.songDAO().getAll();
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on getList of Song");
        }
    }

    public boolean getMusicState() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    musicState = db.gameAppDAO().getMusicState();
                }
            });
            t.start();
            t.join();
            return musicState;
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
            return false;
        }
    }

    public List<Song> getPlayList() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    playList = db.songDAO().getAll();
                }
            });
            t.start();
            t.join();
            return playList;
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
            return new ArrayList<>();
        }
    }

    public int getVolume() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                   volume = db.gameAppDAO().getVolume();
                }
            });
            t.start();
            t.join();
            return volume;
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
            return 0;
        }
    }

    public void setVolume(final int vol) {
        settings.setMusicVol(vol);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    db.gameAppDAO().setVolume(vol);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
        }
    }

    public void setMusicState(final Boolean state) {
        settings.setPlayingMusic(state);
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    db.gameAppDAO().setMusicState(state);
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
        }
    }

    public void setPlayList(final List<Song> aux) {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Song s : aux) {
                        db.songDAO().insertAll((Song) aux);
                    }
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
        }
    }

    public void setCurrentSong(final Song song) {
        if (song == null) return;
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (db.songDAO().findByName(song.getName()) == null) {
                        db.songDAO().insertAll(song);
                    }
                    db.gameAppDAO().setCurrentSong(song.getSource());
                }
            });
            t.start();
            t.join();
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
        }
    }

    public Song getLastPlayedSong() {
        final Song[] song = new Song[1];
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Uri uri = db.gameAppDAO().getCurrentSong();
                    song[0] = db.songDAO().findByUri(uri);
                }
            });
            t.start();
            t.join();
            return song[0];
        } catch (Exception ex) {
            Log.d("PlayList", "initMusicPlayListRepository: Error on setMusicState of Song");
            return null;
        }
    }

    public MusicSettings getSettings() {
        return settings;
    }

}

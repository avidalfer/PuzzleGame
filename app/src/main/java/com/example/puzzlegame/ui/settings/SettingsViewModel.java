package com.example.puzzlegame.ui.settings;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.puzzlegame.model.MusicSettings;
import com.example.puzzlegame.model.Song;
import com.example.puzzlegame.repository.MusicPlayListRepository;

import java.util.List;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<Boolean> _musicState;
    private MutableLiveData<List<Song>> _playList;
    private MutableLiveData<Integer> _volume;
    private MutableLiveData<Song> _lastPlayedSong;
    private MusicPlayListRepository _repo;
    private MusicSettings settings;

    public SettingsViewModel() {
        _musicState = new MutableLiveData<>();
        _playList = new MutableLiveData<>();
        _volume = new MutableLiveData<>();
        _lastPlayedSong = new MutableLiveData<>();
        _repo = MusicPlayListRepository.getInstance();
    }

    public void initRepository(Activity act) {
        _repo.initMusicPlayListRepository(act);
        refreshData();
    }
    private void refreshData() {
        boolean state =_repo.getMusicState();
        settings = _repo.getSettings();
        _musicState.postValue(state);
        List<Song> listSong = _repo.getPlayList();
        _playList.postValue(listSong);
        _volume.postValue(_repo.getVolume());
        _lastPlayedSong.setValue(_repo.getLastPlayedSong());
    }

    public void set_volume(int vol) {
        _volume.setValue(vol);
        _repo.setVolume(vol);
    }

    public void addSong(Song song) {
        List<Song> aux = _playList.getValue();
        if (aux == null) return;
        aux.add(song);
        _playList.setValue(aux);
        _repo.setPlayList(aux);
    }

    public void removeSong(Song song) {
        List<Song> aux = _playList.getValue();
        if (aux == null) return;
        aux.remove(song);
    }

    public void setMusicPlaying(Boolean state) {
        _musicState.setValue(state);
        _repo.setMusicState(state);
    }

    public LiveData<Song> getLastPlayedSongObservable() {
        return _lastPlayedSong;
    }

    public LiveData<Boolean> getMusicStateObservable() {
        return _musicState;
    }

    public LiveData<List<Song>> getPlayListObservable() {
        return _playList;
    }

    public LiveData<Integer> getVolumeObservable() {
        return _volume;
    }

    /**
     * We take Uri stream in an string to set "main" if the current song playing is the main_theme from raw resources
     * @param song
     */
    public void setCurrentSong(Song song) {
        _repo.setCurrentSong(song);
        _lastPlayedSong.setValue(song);
    }

    public void getStoredLastPlayedSong() {
        _lastPlayedSong.postValue(_repo.getLastPlayedSong());
    }


    public MusicSettings getSettings() {
        if (settings == null) return new MusicSettings();
        return settings;
    }
}

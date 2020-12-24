package com.example.puzzlegame.ui.settings;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.MusicSettings;
import com.example.puzzlegame.model.Song;
import com.example.puzzlegame.ui.common.BaseActivity;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class SettingsFragment extends Fragment implements PlayListAdapter.OnSongListener {

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private BaseActivity baseActivity;
    private View _fragmentItems;
    private SwitchCompat onOffMusic;
    private RadioButton rb_officialThemeMusic, rb_newSong, rb_song1, rb_song2, rb_song3,
            radioButton6, radioButton7, radioButton8, radioButton9;
    private SeekBar volumeBar;
    private RecyclerView playListView;
    private Button btn_musicSelection;
    private SettingsViewModel settingsViewModel;
    private RecyclerView.Adapter<PlayListAdapter.MyViewHolder> adapter;
    private List<Song> cachedPlayList;
    private boolean soundActive;
    private MusicSettings settings;


    @Override
    public void onAttach(@NonNull Context activity) {

        super.onAttach(activity);
    }

    public SettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        _fragmentItems = inflater.inflate(R.layout.fragment_scrolling_options, container, false);
        baseActivity = (BaseActivity) getActivity();
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        setViews();
        setSettingsValues();
        setListeners();
        setLiveData();
        return _fragmentItems;
    }

    private void setSettingsValues() {
        settings = settingsViewModel.getSettings();
        boolean isMainThemePlaying = settings.currentSong.toString().equals("main");
        rb_officialThemeMusic.setChecked(isMainThemePlaying);
        onOffMusic.setChecked(settings.playingMusic);
        volumeBar.setProgress(settings.musicVol);
    }

    private void setViews() {
        onOffMusic = _fragmentItems.findViewById(R.id.switchONOFF);
        volumeBar = _fragmentItems.findViewById(R.id.volBar);
        rb_officialThemeMusic = _fragmentItems.findViewById(R.id.rb_officialTheme);
        rb_newSong = _fragmentItems.findViewById(R.id.rb_ownerMusic);

        btn_musicSelection = _fragmentItems.findViewById(R.id.btn_selMusic);
        playListView = _fragmentItems.findViewById(R.id.RV_songs);
        settings = settingsViewModel.getSettings();
    }

    public void setListeners() {
        btn_musicSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_officialThemeMusic.isChecked()) {
                    Song song = new Song(Uri.parse("main"), "main_theme");
                    settingsViewModel.setCurrentSong(song);
                    baseActivity.playRawTheme();
                } else if (rb_newSong.isChecked()) {
                    getSong();
                } else
                    MusicOff();
            }
        });

        onOffMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    soundActive = true;
                    baseActivity.changeSong(null);
                    settingsViewModel.setMusicPlaying(true);
                } else {
                    soundActive = false;
                    baseActivity.pauseMusic();
                    settingsViewModel.setMusicPlaying(false);
                }
            }
        });

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int _setVolume;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _setVolume = progress;
                baseActivity.setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() == 0)
                    baseActivity.pauseMusic();
                else {
                    if (soundActive) {
                        baseActivity.restartSong();
                    }
                }
                settingsViewModel.set_volume(_setVolume);
            }
        });
    }

    private void getSong() {
        onRequestPermissionsResult(REQUEST_READ_EXTERNAL_STORAGE, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new int[]{PackageManager.PERMISSION_GRANTED});
        if (ContextCompat.checkSelfPermission(baseActivity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(baseActivity, Manifest.permission.READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(baseActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, REQUEST_READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Song song = new Song(uri, data.getStringExtra("Name"));
            settingsViewModel.setCurrentSong(song);
        }
    }

    public void setLiveData() {
        final Observer<List<Song>> playListObserver = new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> playlist) {
                if (cachedPlayList == null) {
                    cachedPlayList = playlist;
                }
                inflatePlayList();
                adapter.notifyDataSetChanged();
            }
        };
        settingsViewModel.getPlayListObservable().observe(getViewLifecycleOwner(), playListObserver);

        final Observer<Song> lastPlayedSongObserver = new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                baseActivity.changeSong(song);
            }
        };
        settingsViewModel.getLastPlayedSongObservable().observe(getViewLifecycleOwner(), lastPlayedSongObserver);

        final Observer<Boolean> musicStateObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean state) {
                soundActive = state;
                onOffMusic.setChecked(state);
            }
        };
        settingsViewModel.getMusicStateObservable().observe(getViewLifecycleOwner(), musicStateObserver);

        final Observer<Integer> volumeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer vol) {
                volumeBar.setProgress(vol);
            }
        };
        settingsViewModel.getVolumeObservable().observe(getViewLifecycleOwner(), volumeObserver);
    }

    private void inflatePlayList() {
        adapter = new PlayListAdapter(getContext(), cachedPlayList, this);
        playListView.setAdapter(adapter);
    }

    private void MusicOff() {
        onOffMusic.setChecked(false);
        baseActivity.pauseMusic();
    }

    public void SeleccionReiniciar(View view) {
        if (radioButton6.isChecked()) {
            Intent intent = new Intent();
            Context actividad = null;
            intent.setClass(actividad, actividad.getClass());
            //llamamos a la actividad
            actividad.startActivity(intent);
            //finalizamos la actividad actual

        } else radioButton7.isChecked();
    }

    public void SeleccionSalir(View view) {
        if (radioButton8.isChecked()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else radioButton9.isChecked();
    }

    @Override
    public void onSongClick(int position) {
        //Todo: popup menu PopupMenu popup = new PopupMenu(BaseActivity.this, button);
        //      popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        // popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        // public boolean onMenuItemClick(MenuItem item) {
        // Todo...
        //  return true;
        //  }
        //Todo: mediaplay play theme
        //settingsViewModel.setCurrentSong();
    }
}
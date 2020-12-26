package com.example.puzzlegame.ui.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.Song;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.MyViewHolder> {

    private final List<Song> playlist;
    private final OnSongListener onSongListener;
    private final Context context;

    public PlayListAdapter(Context context, List<Song> playlist, OnSongListener onSongListener) {
        this.playlist = playlist;
        this.context = context;
        this.onSongListener = onSongListener;
    }

    @NonNull
    @Override
    public PlayListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scrolling_adapter, parent, false);
        return new MyViewHolder(viewGroup, onSongListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        boolean last = false;
        if (position == playlist.size()) {
            last = true;
        }
        holder.bind(playlist.get(position), position);
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RadioButton rb_button;
        private final OnSongListener onSongListener;

        public MyViewHolder(@NonNull View itemView, OnSongListener onSongListener) {
            super(itemView);
            rb_button = itemView.findViewById(R.id.rb_song);
            this.onSongListener = onSongListener;

            rb_button.setOnClickListener(this);
        }

        public void bind(Song song, int position) {
            rb_button.setText(song.getName());
        }

        @Override
        public void onClick(View v) {
            onSongListener.onSongClick(getAdapterPosition());
        }
    }

    public interface OnSongListener {
        void onSongClick(int position);
    }
}

package com.example.puzzlegame.ui.halloffame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameAdapter extends RecyclerView.Adapter<HallOfFameAdapter.MyViewHolder> {

    private final List<Score> scores;
    private final HallOfFame hallOfFame;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hof_adapter, parent, false);
        return new MyViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(scores.get(position), position);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public HallOfFameAdapter(Context context, HallOfFame hof, List<Score> scores) {
        this.scores = scores;
        this.hallOfFame = hof;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView positionTView;
        private final TextView nameTView;
        private final TextView scoreTView;

        public MyViewHolder(View view) {
           super(view);
           this.positionTView = (TextView) view.findViewById(R.id.position);
           this.nameTView = (TextView) view.findViewById(R.id.name);
           this.scoreTView = (TextView) view.findViewById(R.id.score);
        }

        public void bind(Score score, int position) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                switch (position) {
                    case 0:
                        positionTView.setTextColor(positionTView.getContext().getColor(R.color.gold));
                        break;
                    case 1:
                        positionTView.setTextColor(positionTView.getContext().getColor(R.color.colorPrimary));
                        break;
                    }
            }
            positionTView.setText(String.valueOf(position+1));
            nameTView.setText(score.getWinnerName());
            scoreTView.setText(score.getFormattedTimeToFinish());
        }
    }
}
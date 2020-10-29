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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlegame.R;
import com.example.puzzlegame.model.LocalHallOfFame;
import com.example.puzzlegame.model.Score;
import com.example.puzzlegame.model.interfaces.HallOfFame;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameAdapter extends RecyclerView.Adapter<HallOfFameAdapter.MyViewHolder> {

    private final List<Score> scores;
    private final long scoreId;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hof_adapter, parent, false);
        return new MyViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(scores.get(position), position, scoreId);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public HallOfFameAdapter(List<Score> scores, long scoreId) {
        this.scores = scores;
        this.scoreId = scoreId;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView positionTView;
        private final TextView nameTView;
        private final TextView scoreTView;

        //on create get the cach the resource view objects
        public MyViewHolder(View view) {
           super(view);
           this.positionTView = (TextView) view.findViewById(R.id.position);
           this.nameTView = (TextView) view.findViewById(R.id.name);
           this.scoreTView = (TextView) view.findViewById(R.id.score);
        }

        //set values and custom appearance for each row of the adapter views
        public void bind(Score score, int position, long scoreId) {
            //check if the score being checked is the very last played game
            //if it is, and is API compatible, highlight the row with color background
            if (score.getId().equals(scoreId)){
                ViewGroup currentRowScore = (ViewGroup) positionTView.getParent();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    currentRowScore.setBackgroundColor(currentRowScore.getContext().getColor(R.color.colorPrimary));
                }
            }

            // set values to the adapter inflated TextViews + set star for 1st position and gold color for 2nd
            positionTView.setText(String.valueOf(position+1));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                switch (position) {
                    case 0:
                        positionTView.setText("");
                        positionTView.setBackgroundResource(R.drawable.ic_baseline_star_rate_24);
                        break;
                    case 1:
                        positionTView.setTextColor(positionTView.getContext().getColor(R.color.gold));
                        break;
                    }
            }
            positionTView.setText(String.valueOf(position+1));
            nameTView.setText(score.getWinnerName());
            scoreTView.setText(score.getFormattedTimeToFinish());
        }
    }
}
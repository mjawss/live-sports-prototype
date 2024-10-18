package com.example.livescoreapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.livescoreapp.LiveScoresResponse;
import java.util.List;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoreViewHolder> {
    private List<LiveScoresResponse.Response> scoresList;

    // Constructor
    public ScoresAdapter(List<LiveScoresResponse.Response> scoresList) {
        this.scoresList = scoresList;
    }

    // ViewHolder class
    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        public ImageView homeTeamBadge, awayTeamBadge;
        public TextView homeTeamName, awayTeamName, score;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            homeTeamBadge = itemView.findViewById(R.id.imageViewHomeTeamBadge);
            awayTeamBadge = itemView.findViewById(R.id.imageViewAwayTeamBadge);
            homeTeamName = itemView.findViewById(R.id.textViewHomeTeam);
            awayTeamName = itemView.findViewById(R.id.textViewAwayTeam);
            score = itemView.findViewById(R.id.textViewScore);
        }
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        LiveScoresResponse.Response match = scoresList.get(position);

        // Bind data
        holder.homeTeamName.setText(match.teams.home.name);
        holder.awayTeamName.setText(match.teams.away.name);
        holder.score.setText(match.goals.home + " - " + match.goals.away);

        // Able to load logos with glide.
        Glide.with(holder.itemView.getContext()).load(match.teams.home.logo).into(holder.homeTeamBadge);
        Glide.with(holder.itemView.getContext()).load(match.teams.away.logo).into(holder.awayTeamBadge);
    }

    @Override
    public int getItemCount() {
        return scoresList.size();
    }
}

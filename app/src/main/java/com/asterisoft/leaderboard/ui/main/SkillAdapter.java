package com.asterisoft.leaderboard.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asterisoft.leaderboard.R;
import com.asterisoft.leaderboard.SkillIqInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillAdapter extends RecyclerView.Adapter<SkillIqHolder> {
    private List<SkillIqInfo> skillIqInfo;
    private static final String SKILL_IQ_SCORE=" skill IQ score, ";

    public SkillAdapter(List<SkillIqInfo> skillIqInfo) {
        this.skillIqInfo = skillIqInfo;
    }

    @NonNull
    @Override
    public SkillIqHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.learner_row,parent,false);
        return new SkillIqHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqHolder holder, int position) {
        Picasso.get()
                .load(skillIqInfo.get(position).getBadgeUrl())
                .into(holder.badge);
        String score= skillIqInfo.get(position).getScore();
        String country= skillIqInfo.get(position).getCountry();
        holder.name.setText(skillIqInfo.get(position).getName());
        holder.bio.setText(score);
        holder.bio.append(SKILL_IQ_SCORE +country);
    }

    @Override
    public int getItemCount() {
        return skillIqInfo.size();
    }
}

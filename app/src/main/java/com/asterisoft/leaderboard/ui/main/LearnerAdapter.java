package com.asterisoft.leaderboard.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asterisoft.leaderboard.LearnerInfo;
import com.asterisoft.leaderboard.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerHolder> {
    private List<LearnerInfo> learnerInfo;
    private static final String HOURS_LEARNT=" learning hours, ";

    public LearnerAdapter(List<LearnerInfo> learnerInfo) {
        this.learnerInfo = learnerInfo;
    }

    @NonNull
    @Override
    public LearnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.learner_row,parent,false);
        return new LearnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerHolder holder, int position) {
        Picasso.get()
                .load(learnerInfo.get(position).getBadgeUrl())
                .into(holder.badge);
        String hours= learnerInfo.get(position).getHours();
        String country= learnerInfo.get(position).getCountry();
        holder.name.setText(learnerInfo.get(position).getName());
        holder.bio.setText(learnerInfo.get(position).getHours());
        holder.bio.append(HOURS_LEARNT+country);
    }

    @Override
    public int getItemCount() {
        return learnerInfo.size();
    }
}

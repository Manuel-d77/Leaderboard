package com.asterisoft.leaderboard.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asterisoft.leaderboard.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnerHolder extends RecyclerView.ViewHolder {
    public TextView name, bio;
    public ImageView badge;
    public LearnerHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.learner_name_textView);
        bio=itemView.findViewById(R.id.learner_details_textView);
        badge=itemView.findViewById(R.id.badge_imageView);
    }
}

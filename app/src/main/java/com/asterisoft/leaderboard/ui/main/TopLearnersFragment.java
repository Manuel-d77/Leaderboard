package com.asterisoft.leaderboard.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asterisoft.leaderboard.LearnerInfo;
import com.asterisoft.leaderboard.R;
import com.asterisoft.leaderboard.services.MainService;
import com.asterisoft.leaderboard.services.ServiceBuilder;

import java.util.List;

public class TopLearnersFragment extends Fragment {
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_top_learners,container,false);
        recyclerView=view.findViewById(R.id.top_learners_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainService taskService= ServiceBuilder.buildService(MainService.class);
        Call<List<LearnerInfo>> call= taskService.getLeadingLearners("hours");
        call.enqueue(new Callback<List<LearnerInfo>>() {
            @Override
            public void onResponse(Call<List<LearnerInfo>> call, Response<List<LearnerInfo>> response) {
                recyclerView.setAdapter(new LearnerAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<LearnerInfo>> call, Throwable t) {
                Toast.makeText(getContext(),"Retrieval Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

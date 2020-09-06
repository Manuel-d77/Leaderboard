package com.asterisoft.leaderboard.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.asterisoft.leaderboard.R;
import com.asterisoft.leaderboard.SkillIqInfo;
import com.asterisoft.leaderboard.services.MainService;
import com.asterisoft.leaderboard.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

public class SkillIqFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainService taskService= ServiceBuilder.buildService(MainService.class);
        Call<List<SkillIqInfo>> request= taskService.getSkillIqLeaders("skilliq");
        request.enqueue(new Callback<List<SkillIqInfo>>() {
            @Override
            public void onResponse(Call<List<SkillIqInfo>> call, Response<List<SkillIqInfo>> response) {
                recyclerView.setAdapter(new SkillAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<SkillIqInfo>> call, Throwable t) {
                Toast.makeText(getContext(),"Retrieval Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_skill_iq, container, false);
        recyclerView=view.findViewById(R.id.skill_iq_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}

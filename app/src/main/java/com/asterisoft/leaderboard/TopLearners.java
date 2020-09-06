package com.asterisoft.leaderboard;

import android.content.Intent;
import android.os.Bundle;

import com.asterisoft.leaderboard.ui.main.SkillIqFragment;
import com.asterisoft.leaderboard.ui.main.TopLearnersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.asterisoft.leaderboard.ui.main.SectionsPagerAdapter;

public class TopLearners extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_learners);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        button=findViewById(R.id.submit_button);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TopLearners.this,Submission.class);
                startActivity(intent);
            }
        });
    }
    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new TopLearnersFragment(),"Learning Leaders");
        sectionsPagerAdapter.addFragment(new SkillIqFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}
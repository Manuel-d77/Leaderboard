package com.asterisoft.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        final Thread launcher= new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(1000*3);
                    startActivity(new Intent(MainActivity.this,TopLearners.class));
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        launcher.start();
    }
}

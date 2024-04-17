package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {
    //Bottom Panel Buttons
    private Button profile;
    private Button home;
    private Button exercise;
    private Button settings;

    //Exercise Buttons
    private Button btn_running;
    private Button btn_hiking;
    private Button btn_cycling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        home = findViewById(R.id.btn_home);
        exercise = findViewById(R.id.btn_exercise);
        profile = findViewById(R.id.btn_profile);
        settings = findViewById(R.id.btn_settings);

        btn_running = findViewById(R.id.btn_runningimage);
        btn_hiking = findViewById(R.id.btn_hikingimage);
        btn_cycling = findViewById(R.id.btn_cyclingimage);
//Bottom Panel ->
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
//end
//ExerciseButtons
        btn_running.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
        btn_hiking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
        btn_cycling.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
    }
}
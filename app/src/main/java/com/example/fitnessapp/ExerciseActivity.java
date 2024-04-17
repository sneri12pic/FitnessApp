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

    //Exercise Buttons
    private Button btn_running;
    private Button btn_hiking;
    private Button btn_cycling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
//Bottom Panel ->
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.btn_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        exercise = findViewById(R.id.btn_exercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
//end

//ExerciseButtons
        btn_running = findViewById(R.id.btn_runningimage);
        btn_running.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
        btn_hiking = findViewById(R.id.btn_hikingimage);
        btn_hiking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
        btn_cycling = findViewById(R.id.btn_cyclingimage);
        btn_cycling.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
                startActivity(intent);
            }
        });
    }
}
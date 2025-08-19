package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.workouts.GymActivity;
import com.example.fitnessapp.workouts.HikingActivity;
import com.example.fitnessapp.workouts.RunningJoggingActivity;

public class ExerciseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Button home = findViewById(R.id.btn_home);
        Button exercise = findViewById(R.id.btn_exercise);
        Button profile = findViewById(R.id.btn_profile);
        Button settings = findViewById(R.id.btn_settings);

        Button btn_running = findViewById(R.id.btn_runningimage);
        Button btn_hiking = findViewById(R.id.btn_hikingimage);
        Button btn_cycling = findViewById(R.id.btn_cyclingimage);
        Button btn_gym = findViewById(R.id.btn_gym_image);
//Bottom Panel ->
        home.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        exercise.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
//end
//ExerciseButtons
        btn_running.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
            startActivity(intent);
        });
        btn_hiking.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, HikingActivity.class);
            startActivity(intent);
        });
        btn_cycling.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, RunningJoggingActivity.class);
            startActivity(intent);
        });
        btn_gym.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseActivity.this, GymActivity.class);
            startActivity(intent);
        });
    }
}
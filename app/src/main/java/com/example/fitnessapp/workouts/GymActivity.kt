package com.example.fitnessapp.workouts;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;


import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.ExerciseActivity;
import com.example.fitnessapp.HomeActivity;
import com.example.fitnessapp.ProfileActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.SettingsActivity;

public class GymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        Chronometer chronometer = findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();  // call stop() to pause


        Button home = findViewById(R.id.btn_home);
        Button exercise = findViewById(R.id.btn_exercise);
        Button profile = findViewById(R.id.btn_profile);
        Button settings = findViewById(R.id.btn_settings);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(GymActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(GymActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        exercise.setOnClickListener(v -> {
            Intent intent = new Intent(GymActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(GymActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}

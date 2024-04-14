package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RunningActivity extends AppCompatActivity {
    //Bottom Panel Buttons
    private Button profile;
    private Button home;
    private Button exercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
//Bottom Panel ->
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.btn_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        exercise = findViewById(R.id.btn_exercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
//end
    }
}
package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    private Button home;
    private Button profile;
    private Button exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        profile = findViewById(R.id.btn_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        exercise = findViewById(R.id.btn_exercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
    }
    public void goToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
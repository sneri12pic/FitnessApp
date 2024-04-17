package com.example.fitnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private Button profile;
    private Button home;
    private Button exercise;
    private Button updateButton;
    private ProgressBar progressBarSteps;
    private EditText editText;
    private TextView stepsCounterFilled;
    private TextView stepsCounterFilledStroked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        home = findViewById(R.id.btn_home);
        exercise = findViewById(R.id.btn_exercise);
        updateButton = findViewById(R.id.btn_update);

        progressBarSteps = findViewById(R.id.progress_bar_steps);
        editText = findViewById(R.id.editText);
        stepsCounterFilled = findViewById(R.id.steps_counter_filled);
        stepsCounterFilledStroked = findViewById(R.id.steps_counter_filled_stroked);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
        //PROGRESS BAR
        progressBarSteps = findViewById(R.id.progress_bar_steps);

        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get text from the EditText
                String inputText = editText.getText().toString();
                // Set the input text to the TextViews
                stepsCounterFilled.setText(inputText);
                stepsCounterFilledStroked.setText(inputText);

            }
        });
    }
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
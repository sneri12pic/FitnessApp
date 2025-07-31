package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    private Button profile;
    private Button home;
    private Button exercise;
    private Button settings;
    private Button updateButton;
    private EditText editText;
    private TextView stepsCounterFilled;
    private TextView stepsCounterFilledStroked;

    private EditText inputLimit, inputCalories;
    private TextView statusText;
    private GaugeView gaugeView;
    private int calorieLimit = 2000;
    private int consumedCalories = 0;
    private ImageView imgAddFood;

    private boolean isEditTextVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        home = findViewById(R.id.btn_home);
        exercise = findViewById(R.id.btn_exercise);
        settings = findViewById(R.id.btn_settings);
        updateButton = findViewById(R.id.btn_update);

        editText = findViewById(R.id.editText);
        stepsCounterFilled = findViewById(R.id.steps_counter_filled);
        stepsCounterFilledStroked = findViewById(R.id.steps_counter_filled_stroked);

        inputLimit = findViewById(R.id.inputLimit);
        inputCalories = findViewById(R.id.inputCalories);
        statusText = findViewById(R.id.statusText);
        gaugeView = findViewById(R.id.gaugeView);
        imgAddFood = findViewById(R.id.img_add_food);

        Button btnAddFood = findViewById(R.id.btnAddFood);
        btnAddFood.setOnClickListener(v -> {
            String foodStr = inputCalories.getText().toString();
            if (!foodStr.isEmpty()) {
                consumedCalories += Integer.parseInt(foodStr);
            }
            inputCalories.setText("");
            float percent = (float) consumedCalories / calorieLimit;
            gaugeView.setPercent(percent);
            statusText.setText("Calories: " + consumedCalories + " / " + calorieLimit);
        });

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
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        updateButton.setOnClickListener(v -> {
            if (!isEditTextVisible) {
                inputCalories.setVisibility(View.INVISIBLE);
                btnAddFood.setVisibility(View.INVISIBLE);
                imgAddFood.setVisibility(View.INVISIBLE);
                inputLimit.setVisibility(View.VISIBLE);

                // Get text from the EditText
                String inputText = editText.getText().toString();
                // Set the input text to the TextViews
                stepsCounterFilled.setText(inputText);
                stepsCounterFilledStroked.setText(inputText);
                // Show the TextViews and hide the EditText
                stepsCounterFilled.setVisibility(View.VISIBLE);
                stepsCounterFilledStroked.setVisibility(View.VISIBLE);
                editText.setVisibility(View.INVISIBLE);
                // Clear focus and hide the keyboard
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                // Update the flag
                isEditTextVisible = true;
            } else {
                String limitStr = inputLimit.getText().toString();

                if (!limitStr.isEmpty()) {
                    calorieLimit = Integer.parseInt(limitStr);
                }
                float percent = (float) consumedCalories / calorieLimit;
                gaugeView.setPercent(percent);
                statusText.setText("Calories: " + consumedCalories + " / " + calorieLimit);
                inputCalories.setVisibility(View.VISIBLE);
                btnAddFood.setVisibility(View.VISIBLE);
                imgAddFood.setVisibility(View.VISIBLE);
                inputLimit.setVisibility(View.INVISIBLE);

                // Update the flag
                isEditTextVisible = false;
            }
        });
    }
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
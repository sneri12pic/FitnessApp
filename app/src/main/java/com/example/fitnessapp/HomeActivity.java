package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    private Button profile;
    private Button home;
    private Button exercise;
    private Button updateButton;
    private EditText editText;
    private TextView stepsCounterFilled;
    private TextView stepsCounterFilledStroked;

    private boolean isEditTextVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        home = findViewById(R.id.btn_home);
        exercise = findViewById(R.id.btn_exercise);
        updateButton = findViewById(R.id.btn_update);

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
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isEditTextVisible) {
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
                    // Toggle visibility of EditText and TextViews
                    stepsCounterFilled.setVisibility(View.INVISIBLE);
                    stepsCounterFilledStroked.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    // Clear any existing text in EditText
                    editText.setText("");
                    // Request focus and show the keyboard
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                    // Update the flag
                    isEditTextVisible = false;
                }
            }
        });
    }
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
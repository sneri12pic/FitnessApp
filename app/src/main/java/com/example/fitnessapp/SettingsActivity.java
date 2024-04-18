package com.example.fitnessapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private Button goBack;
    private Button btnHeight, btnWeight, btnEnergy;
    private EditText etHeight, etWeight, etEnergy;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        goBack = findViewById(R.id.btn_go_back);
        btnHeight = findViewById(R.id.btn_height);
        btnWeight = findViewById(R.id.btn_weight);
        btnEnergy = findViewById(R.id.btn_energy);


        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etEnergy = findViewById(R.id.et_energy);


        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Set saved values to EditText fields
        etHeight.setText(sharedPreferences.getString("height", ""));
        etWeight.setText(sharedPreferences.getString("weight", ""));
        etEnergy.setText(sharedPreferences.getString("energy", ""));

        // Set click listeners for buttons
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnHeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etHeight.setText("Button Height Clicked");
            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etWeight.setText("Button Weight Clicked");
            }
        });

        btnEnergy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                etEnergy.setText("Button Energy Clicked");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save EditText values to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("height", etHeight.getText().toString());
        editor.putString("weight", etWeight.getText().toString());
        editor.putString("energy", etEnergy.getText().toString());
        editor.apply();
    }
}

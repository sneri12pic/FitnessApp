package com.example.fitnessapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    private EditText etHeight, etWeight, etEnergy;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        Button goBack = findViewById(R.id.btn_go_back);
        Button btnHeight = findViewById(R.id.btn_height);
        Button btnWeight = findViewById(R.id.btn_weight);
        Button btnEnergy = findViewById(R.id.btn_energy);
        Button btnTheme = findViewById(R.id.btn_theme);

        //Drop List for themes
        List<String> themes = Arrays.asList("Pink", "Dark");
        Spinner themeSpinner = findViewById(R.id.theme_spinner);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, themes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

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
        goBack.setOnClickListener(v -> onBackPressed());

        btnHeight.setOnClickListener(v -> etHeight.setText("Button Height Clicked"));

        btnWeight.setOnClickListener(v -> etWeight.setText("Button Weight Clicked"));

        btnEnergy.setOnClickListener(v -> etEnergy.setText("Button Energy Clicked"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save EditText values to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor height = editor.putString("height", etHeight.getText().toString());
        SharedPreferences.Editor weight = editor.putString("weight", etWeight.getText().toString());
        SharedPreferences.Editor energy = editor.putString("energy", etEnergy.getText().toString());
        editor.apply();
    }
}

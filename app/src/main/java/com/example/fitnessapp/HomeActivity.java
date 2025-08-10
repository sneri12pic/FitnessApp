package com.example.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.fitnessapp.dao.CaloriesTrackerDao;
import com.example.fitnessapp.dao.UserProfileDao;
import com.example.fitnessapp.db.AppDatabase;
import com.example.fitnessapp.model.CaloriesTracker;
import com.example.fitnessapp.model.UserProfile;

import java.util.Date;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HomeActivity extends AppCompatActivity {

    private CaloriesTrackerDao daoCalories;
    private UserProfileDao daoProfile;
    private UserProfile currentUser;
    private int caloriesLimit;
    private float percent;

    private EditText inputLimit, inputCalories;
    private TextView statusText;
    private GaugeView gaugeView;
    private ImageView imgAddFood;

    private Button btnAddFood, updateButton;
    private boolean isEditTextVisible = false;
    private int consumedCalories;
    private int calFood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create an instance of database(To Save CaloriesTracker data)
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fitness_db")
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();

        daoCalories = db.caloriesTrackerDao();
        daoProfile = db.userProfileDao();

        CaloriesTracker caloriesTracker = new CaloriesTracker();

        // Calorie Tracker UI binders
        inputLimit = findViewById(R.id.input_limit);
        inputCalories = findViewById(R.id.inputCalories);
        statusText = findViewById(R.id.statusText);
        gaugeView = findViewById(R.id.gaugeView);
        imgAddFood = findViewById(R.id.img_add_food);
        btnAddFood = findViewById(R.id.btnAddFood);
        updateButton = findViewById(R.id.btn_update);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Check if user exists
            List<UserProfile> allUsers = daoProfile.getAllProfiles();
            if (allUsers.isEmpty()) {
                // Create default user
                UserProfile newUser = new UserProfile();
                newUser.name = "Alex";
                newUser.caloriesLimit = 2000; // default limit
                daoProfile.insertProfile(newUser);

                allUsers = daoProfile.getAllProfiles();
            }

            // Use the first user
            currentUser = allUsers.get(0);
            int currentUserId = currentUser.id;
            caloriesLimit = currentUser.caloriesLimit;

            // Initialise our calories tracker class
            CaloriesTracker newEntry = new CaloriesTracker();

            // Load consumed calories for this user
            List<CaloriesTracker> entries = daoCalories.getEntriesForUser(currentUserId);
            consumedCalories = 0;
            for (CaloriesTracker entry : entries) {
                consumedCalories += entry.calories;
            }
            percent = (float) consumedCalories / caloriesLimit;
            // Update UI on main thread
            runOnUiThread(() -> {
                gaugeView.setPercent(percent);
                statusText.setText("Calories: " + consumedCalories + " / " + caloriesLimit);

                // Safe to use userProfile and daoProfile
                btnAddFood.setOnClickListener(v -> {

                    String foodStr = inputCalories.getText().toString();
                    if (!foodStr.isEmpty()) {
                        calFood = Integer.parseInt(foodStr);



                        newEntry.calories = calFood;
                        consumedCalories += calFood;
                        newEntry.consumedCalories = consumedCalories;
                        newEntry.date = new Date();
                        newEntry.userId = currentUserId;
                        executor.execute(() -> daoCalories.insert(newEntry));

                        inputCalories.setText("");
                        percent = (float) consumedCalories / caloriesLimit;
                        gaugeView.setPercent(percent);
                        statusText.setText("Calories: " + consumedCalories + " / " + caloriesLimit);
                    }
                });
                updateButton.setOnClickListener(v -> {
                    if (!isEditTextVisible) {
                        inputCalories.setVisibility(View.INVISIBLE);
                        btnAddFood.setVisibility(View.INVISIBLE);
                        imgAddFood.setVisibility(View.INVISIBLE);
                        inputLimit.setVisibility(View.VISIBLE);

                        // Update the flag
                        isEditTextVisible = true;
                    } else {
                        // Input Calories Limit to Database
                        String limitStr = inputLimit.getText().toString();

                        if (!limitStr.isEmpty()) {
                            currentUser.caloriesLimit = Integer.parseInt(limitStr);

                            // Inset Calories Limit to DataBase
                            executor.execute(() -> daoProfile.update(currentUser));
                            caloriesLimit = currentUser.caloriesLimit;
                        }
                        percent = (float) consumedCalories / caloriesLimit;
                        gaugeView.setPercent(percent);
                        statusText.setText("Calories: " + consumedCalories + " / " + caloriesLimit);
                        inputCalories.setVisibility(View.VISIBLE);
                        btnAddFood.setVisibility(View.VISIBLE);
                        imgAddFood.setVisibility(View.VISIBLE);
                        inputLimit.setVisibility(View.INVISIBLE);

                        // Update the flag
                        isEditTextVisible = false;
                    }
                });
            });
        });

        // Bottom panel navigation
        findViewById(R.id.btn_profile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        findViewById(R.id.btn_home).setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        findViewById(R.id.btn_exercise).setOnClickListener(v -> startActivity(new Intent(this, ExerciseActivity.class)));
        findViewById(R.id.btn_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
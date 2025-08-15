package com.example.fitnessapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fitnessapp.dao.UserProfileDao;
import com.example.fitnessapp.db.AppDatabase;
import com.example.fitnessapp.model.UserProfile;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {

    private UserProfileDao dao;
    private UserProfile currentUser;
    private final Executor executor = Executors.newSingleThreadExecutor();

    // Func to convert values dp to px
/*    private int dpToPx(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }*/

    // For Automatic set of week day
    private final int[] panelLeftMargins = {
            R.dimen.panel_sunday_left,
            R.dimen.panel_monday_left,
            R.dimen.panel_tuesday_left,
            R.dimen.panel_wednesday_left,
            R.dimen.panel_thursday_left,
            R.dimen.panel_friday_left,
            R.dimen.panel_saturday_left
    };


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Create an instance of database(To Save Image URI)
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "fitness_db")
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();
        
        dao = db.userProfileDao();

        // Profile Picture
        ImageView profilePic = findViewById(R.id.img_profilePic);

        executor.execute(() -> {
            // Load first user (same as HomeActivity)
            if (dao.getAllProfiles().isEmpty()) {
                UserProfile newUser = new UserProfile();
                newUser.name = "Alex";
                newUser.caloriesLimit = 2000;
                long newId = dao.insertProfile(newUser);
                newUser.id = (int) newId;
                currentUser = newUser;
            } else {
                currentUser = dao.getAllProfiles().get(0);
            }
            // Update UI on main thread
            runOnUiThread(() -> {
                if (currentUser.photoUri != null) {
                    Uri savedUri = Uri.parse(currentUser.photoUri);
                    Glide.with(this)
                            .load(savedUri)
                            .transform(new CircleCrop())
                            .placeholder(R.drawable.profile_icon_picture)  // Or your default placeholder
                            .into(profilePic);
                }
            });
        });

        ImageView panelImage = findViewById(R.id.img_panel);

        Button btn_panelMonday = findViewById(R.id.btn_panelMonday);
        Button btn_panelTuesday = findViewById(R.id.btn_panelTuesday);
        Button btn_panelWednesday = findViewById(R.id.btn_panelWednesday);
        Button btn_panelThursday = findViewById(R.id.btn_panelThursday);
        Button btn_panelFriday = findViewById(R.id.btn_panelFriday);
        Button btn_panelSaturday = findViewById(R.id.btn_panelSaturday);
        Button btn_panelSunday = findViewById(R.id.btn_panelSunday);

        // Set Profile Picture Animation
        profilePic.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Animation scaleUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                v.startAnimation(scaleUp);
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down);
                v.startAnimation(scaleDown);
            }
            return false;
        });

        profilePic.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        
        // Get the current LayoutParams
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) panelImage.getLayoutParams();

        // Get the day of the week to put correct menu tab of the exercise box
        int dayIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        setPanelPosition(panelImage,
                getResources().getDimension(panelLeftMargins[dayIndex]),
                getResources().getDimension(R.dimen.panel_dayOfWeek_top));

//---------------------------------------------------------------------------------------------------------------Buttons Week Days
        btn_panelMonday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_monday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelTuesday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_tuesday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelWednesday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_wednesday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelThursday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_thursday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelFriday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_friday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelSaturday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_saturday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
        btn_panelSunday.setOnClickListener(v -> setPanelPosition(panelImage, getResources().getDimension(R.dimen.panel_sunday_left), getResources().getDimension(R.dimen.panel_dayOfWeek_top)));
//---------------------------------------------------------------------------------------------------------------<

//---------------------------------------------------------------------------------------------------------------Buttons Bottom Panel
        // Bottom panel buttons
        findViewById(R.id.btn_home).setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        findViewById(R.id.btn_exercise).setOnClickListener(v -> startActivity(new Intent(this, ExerciseActivity.class)));
        findViewById(R.id.btn_profile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        findViewById(R.id.btn_settings).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
//---------------------------------------------------------------------------------------------------------------<
    // Handles the result of Picture Upload
    private final ActivityResultLauncher<String> pickImageLauncher =
        registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                ImageView profilePicture = findViewById(R.id.img_profilePic);
                Glide.with(this)
                        .load(uri)
                        .transform(new CircleCrop())
                        .placeholder(R.drawable.profile_icon_picture)
                        .into(profilePicture);
                // Save URI to existing user
                currentUser.photoUri = uri.toString();
                executor.execute(() -> dao.update(currentUser));
            }
        });

    // Helper for set panel position
    private void setPanelPosition(View panelImage, float leftDp, float topDp) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) panelImage.getLayoutParams();
        params.setMargins((int) leftDp, (int) topDp, 0, 0);
        panelImage.setLayoutParams(params);
    }

}
package com.example.fitnessapp;

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
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

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

        profilePic.setOnClickListener(v -> {
           Intent intent = new Intent(Intent.ACTION_PICK);
           intent.setType("image/*");
           startActivityForResult(intent, 1);
       });
        
        // Get the current LayoutParams
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) panelImage.getLayoutParams();

        // Get the day of the week to put correct menu tab of the exercise box
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //day order: 1 = Sunday, 2 = Monday, ..., 7 = Saturday

        //MarginLeft step is 37.2 which is rounded
        switch(dayOfWeek){
            case Calendar.SUNDAY:
                params.setMargins(dpToPx(295), dpToPx(452), 0, 0);
                break;
            case Calendar.MONDAY:
                params.setMargins(dpToPx(72), dpToPx(452), 0, 0);
                break;
            case Calendar.TUESDAY:
                params.setMargins(dpToPx(111), dpToPx(452), 0, 0);
                break;
            case Calendar.WEDNESDAY:
                params.setMargins(dpToPx(146), dpToPx(452), 0, 0);
                break;
            case Calendar.THURSDAY:
                params.setMargins(dpToPx(183), dpToPx(452), 0, 0);
                break;
            case Calendar.FRIDAY:
                params.setMargins(dpToPx(220), dpToPx(452), 0, 0);
                break;
            case Calendar.SATURDAY:
                params.setMargins(dpToPx(258), dpToPx(452), 0, 0);
                break;
        }
//---------------------------------------------------------------------------------------------------------------Buttons Week Days
        btn_panelMonday.setOnClickListener(v -> {
            params.setMargins(dpToPx(72), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelTuesday.setOnClickListener(v -> {
            params.setMargins(dpToPx(111), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelWednesday.setOnClickListener(v -> {
            params.setMargins(dpToPx(146), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelThursday.setOnClickListener(v -> {
            params.setMargins(dpToPx(183), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelFriday.setOnClickListener(v -> {
            params.setMargins(dpToPx(220), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelSaturday.setOnClickListener(v -> {
            params.setMargins(dpToPx(258), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });
        btn_panelSunday.setOnClickListener(v -> {
            params.setMargins(dpToPx(295), dpToPx(452), 0, 0);
            panelImage.setLayoutParams(params);
        });

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView profilePicture = findViewById(R.id.img_profilePic);
            Glide.with(this)
                    .load(selectedImage)
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.profile_icon_picture)
                    .into(profilePicture);

            // Save URI to existing user
            currentUser.photoUri = selectedImage.toString();
            executor.execute(() -> dao.update(currentUser));
        }
    }
}
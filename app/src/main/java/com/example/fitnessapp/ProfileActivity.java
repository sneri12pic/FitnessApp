package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    // Exercise Panel
    private ImageView panelImage;
    // Func to convert values dp to px
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Bottom Panel
        Button home = findViewById(R.id.btn_home);
        Button exercise = findViewById(R.id.btn_exercise);
        Button profile = findViewById(R.id.btn_profile);
        Button settings = findViewById(R.id.btn_settings);

        panelImage = findViewById(R.id.img_panel);

        Button btn_panelMonday = findViewById(R.id.btn_panelMonday);
        Button btn_panelTuesday = findViewById(R.id.btn_panelTuesday);
        Button btn_panelWednesday = findViewById(R.id.btn_panelWednesday);
        Button btn_panelThursday = findViewById(R.id.btn_panelThursday);
        Button btn_panelFriday = findViewById(R.id.btn_panelFriday);
        Button btn_panelSaturday = findViewById(R.id.btn_panelSaturday);
        Button btn_panelSunday = findViewById(R.id.btn_panelSunday);

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
        home.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        exercise.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
    public void goToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
//---------------------------------------------------------------------------------------------------------------<
}
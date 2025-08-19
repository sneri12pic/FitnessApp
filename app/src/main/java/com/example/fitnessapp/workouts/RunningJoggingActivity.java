package com.example.fitnessapp.workouts;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.ExerciseActivity;
import com.example.fitnessapp.HomeActivity;
import com.example.fitnessapp.ProfileActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.SettingsActivity;

public class RunningJoggingActivity extends AppCompatActivity {

    // Func to convert values dp to px
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    private ImageView runningDetailsImageOpen;
    private ImageView runningDetailsImageClosed;
    private ImageView runningDetailsText;
    private RelativeLayout runningDetailsContainer;

    private ImageView joggingDetailsImageOpen;
    private ImageView joggingDetailsImageClosed;
    private ImageView joggingDetailsText;
    private RelativeLayout joggingDetailsContainer;
    private boolean isRunningActivityOpen = false;
    private boolean isJoggingActivityOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_jogging);


        // Running Details--------------------------------
        Button runningButton = findViewById(R.id.running_button);
        runningDetailsImageOpen = findViewById(R.id.running_details_image_opened);
        runningDetailsImageClosed = findViewById(R.id.running_details_image_closed);
        runningDetailsText = findViewById(R.id.running_details_text);
        runningDetailsContainer = findViewById(R.id.running_details_container);

        // Jogging Details--------------------------------
        Button joggingButton = findViewById(R.id.jogging_button);
        joggingDetailsImageOpen = findViewById(R.id.jogging_details_image_opened);
        joggingDetailsImageClosed = findViewById(R.id.jogging_details_image_closed);
        joggingDetailsText = findViewById(R.id.jogging_details_text);
        joggingDetailsContainer = findViewById(R.id.jogging_details_container);
        TextView joggingDetailsTitle = findViewById(R.id.jogging_title);

        // Bottom Panel Details--------------------------------
        Button home = findViewById(R.id.btn_home);
        Button profile = findViewById(R.id.btn_profile);
        Button exercise = findViewById(R.id.btn_exercise);
        Button settings = findViewById(R.id.btn_settings);

        // Bottom Panel Buttons-------------------
        home.setOnClickListener(v -> {
            Intent intent = new Intent(RunningJoggingActivity.this, HomeActivity.class);
            startActivity(intent);
        });
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(RunningJoggingActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
        exercise.setOnClickListener(v -> {
            Intent intent = new Intent(RunningJoggingActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(RunningJoggingActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
        // Running/Jogging Buttons----------------
        RelativeLayout.MarginLayoutParams paramsJogCon = (RelativeLayout.MarginLayoutParams) joggingDetailsContainer.getLayoutParams();

        RelativeLayout.MarginLayoutParams imageOpenedJogDet = (RelativeLayout.MarginLayoutParams) joggingDetailsImageOpen.getLayoutParams();

        runningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toggleRunningDetails();
                if (runningDetailsContainer.getVisibility() == View.VISIBLE) {
                    // Running activity is closed
                    isRunningActivityOpen = false;
                    // If running details are visible, hide them
                    runningDetailsContainer.setVisibility(View.INVISIBLE);
                    runningDetailsImageOpen.setVisibility(View.INVISIBLE);
                    runningDetailsText.setVisibility(View.INVISIBLE);
                    runningDetailsImageClosed.setVisibility(View.VISIBLE);

                    paramsJogCon.setMargins(dpToPx(0), dpToPx(420), 0, 0);
                    joggingDetailsContainer.setLayoutParams(paramsJogCon);

                    imageOpenedJogDet.setMargins(dpToPx(0), dpToPx(0), 0, 0);
                    joggingDetailsImageClosed.setLayoutParams(imageOpenedJogDet);
                }
                else
                {
                    // Running activity is open
                    isRunningActivityOpen = true;
                    // If running details are not visible, show them
                    runningDetailsContainer.setVisibility(View.VISIBLE);
                    runningDetailsImageOpen.setVisibility(View.VISIBLE);
                    runningDetailsText.setVisibility(View.VISIBLE);
                    runningDetailsImageClosed.setVisibility(View.INVISIBLE);

                    paramsJogCon.setMargins(dpToPx(0), dpToPx(730), 0, 0);
                    joggingDetailsContainer.setLayoutParams(paramsJogCon);

                    imageOpenedJogDet.setMargins(dpToPx(0), dpToPx(0), 0, 0);
                    joggingDetailsImageClosed.setLayoutParams(imageOpenedJogDet);
                }
            }
        });

        joggingButton.setOnClickListener(v -> {
            if (joggingDetailsImageClosed.getVisibility() == View.VISIBLE) {
                isJoggingActivityOpen = false;
                joggingDetailsContainer.setVisibility(View.VISIBLE);
                joggingDetailsImageOpen.setVisibility(View.VISIBLE);
                joggingDetailsImageClosed.setVisibility(View.INVISIBLE);

                joggingDetailsText.setVisibility(View.VISIBLE);
            } else {
                isJoggingActivityOpen = true;
                // If jogging details are not visible, show them
                joggingDetailsContainer.setVisibility(View.VISIBLE);
                joggingDetailsImageOpen.setVisibility(View.INVISIBLE);
                joggingDetailsImageClosed.setVisibility(View.VISIBLE);

                joggingDetailsText.setVisibility(View.INVISIBLE);
            }
        });
    }
}

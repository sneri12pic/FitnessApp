package com.example.fitnessapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class RunningJoggingActivity extends AppCompatActivity {
    // Bottom Panel Buttons--------------------------------
    private Button profile;
    private Button home;
    private Button exercise;
    private Button settings;

    // Running Details--------------------------------
    private Button runningButton;
    private ImageView runningDetailsImageOpen;
    private ImageView runningDetailsImageClosed;
    private ImageView runningDetailsText;
    private RelativeLayout runningDetailsContainer;

    // Jogging Details--------------------------------
    private Button joggingButton;
    private ImageView joggingDetailsImageOpen;
    private ImageView joggingDetailsImageClosed;
    private ImageView joggingDetailsText;
    private RelativeLayout joggingDetailsContainer;
    private TextView joggingDetailsTitle;

    // Initial top margin for jogging details container
    private final int INITIAL_JOGGING_MARGIN_TOP = 310;
    private boolean isRunningActivityOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_jogging);
        // Bottom Panel Buttons-------------------
        home = findViewById(R.id.btn_home);
        profile = findViewById(R.id.btn_profile);
        exercise = findViewById(R.id.btn_exercise);
        settings = findViewById(R.id.btn_settings);
        // Running--------------------------------
        runningButton = findViewById(R.id.running_button);
        runningDetailsImageOpen = findViewById(R.id.running_details_image_opened);
        runningDetailsImageClosed = findViewById(R.id.running_details_image_closed);
        runningDetailsText = findViewById(R.id.running_details_text);
        runningDetailsContainer = findViewById(R.id.running_details_container);
        // Jogging--------------------------------
        joggingButton = findViewById(R.id.jogging_button);
        joggingDetailsImageOpen = findViewById(R.id.jogging_details_image_opened);
        joggingDetailsImageClosed = findViewById(R.id.jogging_details_image_closed);
        joggingDetailsText = findViewById(R.id.jogging_details_text);
        joggingDetailsContainer = findViewById(R.id.jogging_details_container);
        joggingDetailsTitle = findViewById(R.id.jogging_title);
        // Bottom Panel Buttons-------------------
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningJoggingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningJoggingActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningJoggingActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RunningJoggingActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        // Running/Jogging Buttons----------------
        runningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRunningDetails();
            }
        });
        joggingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleJoggingDetails();
            }
        });
    }
    private void toggleRunningDetails() {
        // Toggle visibility of running details
        if (runningDetailsContainer.getVisibility() == View.VISIBLE) {
            // Running activity is closed
            isRunningActivityOpen = false;
            // Adjust margin for jogging button
            adjustJoggingButtonMargin(INITIAL_JOGGING_MARGIN_TOP);
            // If running details are visible, hide them
            runningDetailsContainer.setVisibility(View.INVISIBLE);
            runningDetailsImageOpen.setVisibility(View.INVISIBLE);
            runningDetailsText.setVisibility(View.INVISIBLE);
            runningDetailsImageClosed.setVisibility(View.VISIBLE);
            // Restore default margin for jogging button
            adjustJoggingButtonMargin(INITIAL_JOGGING_MARGIN_TOP);
            // Ensure jogging details are closed
            //closeJoggingDetails();
        } else {
            // Running activity is open
            isRunningActivityOpen = true;
            // If running details are not visible, show them
            runningDetailsContainer.setVisibility(View.VISIBLE);
            runningDetailsImageOpen.setVisibility(View.VISIBLE);
            runningDetailsText.setVisibility(View.VISIBLE);
            runningDetailsImageClosed.setVisibility(View.INVISIBLE);
            // Adjust margin for jogging button
            int runningDetailsHeight = runningDetailsContainer.getHeight();
            adjustJoggingButtonMargin(runningDetailsHeight+50);
        }
    }
    public void toggleJoggingDetails() {
        // Toggle visibility of jogging details
        if (joggingDetailsContainer.getVisibility() == View.VISIBLE) {
            joggingDetailsContainer.setVisibility(View.INVISIBLE);
            joggingDetailsImageOpen.setVisibility(View.INVISIBLE);
            joggingDetailsImageClosed.setVisibility(View.VISIBLE);
        } else {
            // If jogging details are not visible, show them
            joggingDetailsContainer.setVisibility(View.VISIBLE);
            joggingDetailsImageOpen.setVisibility(View.VISIBLE);
            joggingDetailsImageClosed.setVisibility(View.INVISIBLE);
            if(isRunningActivityOpen){
                // Adjust margins for Water details
                adjustJoggingButtonMargin(runningDetailsContainer.getHeight()+50);

            }
            else{
                adjustJoggingButtonMargin(INITIAL_JOGGING_MARGIN_TOP);
            }
        }
    }
    //Adjustment For The Jogging Tab
    private void adjustJoggingButtonMargin(int marginTop) {
        // Adjust the margins for all elements within the jogging details container
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) joggingDetailsContainer.getLayoutParams();
        layoutParams.setMargins(0, marginTop + 550, 0, 0);
        joggingDetailsContainer.setLayoutParams(layoutParams);

        // Adjust the margins for all elements within the jogging details joggingDetailsImageClosed
        RelativeLayout.LayoutParams imageClosedLayoutParams = (RelativeLayout.LayoutParams) joggingDetailsImageClosed.getLayoutParams();
        imageClosedLayoutParams.setMargins(0, marginTop - 31, 0, 0);
        joggingDetailsImageClosed.setLayoutParams(imageClosedLayoutParams);

        // Adjust the margins for the jogging title
        RelativeLayout.LayoutParams titleLayoutParams = (RelativeLayout.LayoutParams) joggingDetailsTitle.getLayoutParams();
        titleLayoutParams.setMargins(45, marginTop, 0, 0);
        joggingDetailsTitle.setLayoutParams(titleLayoutParams);

        // Adjust the margins for the jogging buttons (if needed)
        RelativeLayout.LayoutParams button1LayoutParams = (RelativeLayout.LayoutParams) joggingButton.getLayoutParams();
        button1LayoutParams.setMargins(0, marginTop, 0, 0);
        joggingButton.setLayoutParams(button1LayoutParams);
    }
}

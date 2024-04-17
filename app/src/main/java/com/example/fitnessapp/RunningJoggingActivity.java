package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RunningJoggingActivity extends AppCompatActivity {
    // Bottom Panel Buttons
    private Button profile;
    private Button home;
    private Button exercise;

    // Running Details
    private Button runningButton;
    private ImageView runningDetailsImageOpen;
    private ImageView runningDetailsImageClosed;
    private ImageView runningDetailsText;
    private RelativeLayout runningDetailsContainer;

    // Jogging Details
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

        home = findViewById(R.id.btn_home);
        profile = findViewById(R.id.btn_profile);
        exercise = findViewById(R.id.btn_exercise);

        runningButton = findViewById(R.id.running_button);
        runningDetailsImageOpen = findViewById(R.id.running_details_image_opened);
        runningDetailsImageClosed = findViewById(R.id.running_details_image_closed);
        runningDetailsText = findViewById(R.id.running_details_text);
        runningDetailsContainer = findViewById(R.id.running_details_container);

        joggingButton = findViewById(R.id.jogging_button);
        joggingDetailsImageOpen = findViewById(R.id.jogging_details_image_opened);
        joggingDetailsImageClosed = findViewById(R.id.jogging_details_image_closed);
        joggingDetailsText = findViewById(R.id.jogging_details_text);
        joggingDetailsContainer = findViewById(R.id.jogging_details_container);
        joggingDetailsTitle = findViewById(R.id.jogging_title);

        // Bottom Panel Buttons ->
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
        // Bottom Panel Buttons End

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
            // Running activity is open
            isRunningActivityOpen = true;
            // Adjust margin for jogging button
            adjustJoggingButtonMargin(400);
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
            // Running activity is closed
            isRunningActivityOpen = false;
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
            // If jogging details are visible, hide them
            closeJoggingDetails();
        } else {
            // If jogging details are not visible, show them
            joggingDetailsContainer.setVisibility(View.VISIBLE);
            joggingDetailsImageOpen.setVisibility(View.VISIBLE);
            joggingDetailsImageClosed.setVisibility(View.INVISIBLE);

            // Add a layout listener to wait for the running details container to be laid out
            joggingDetailsContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // Calculate the height of the running details container
                    int runningDetailsHeight = runningDetailsContainer.getHeight();

                    // Adjust margin for jogging button to position it below the opened running tab
                    adjustJoggingButtonMargin(runningDetailsHeight+50);

                    // Remove the layout listener to prevent multiple adjustments
                    joggingDetailsContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }


    private void closeRunningDetails() {
        // Close running details
        runningDetailsContainer.setVisibility(View.INVISIBLE);
        runningDetailsImageOpen.setVisibility(View.INVISIBLE);
        runningDetailsText.setVisibility(View.INVISIBLE);
        runningDetailsImageClosed.setVisibility(View.VISIBLE);
    }

    private void closeJoggingDetails() {
        // Close jogging details
        joggingDetailsContainer.setVisibility(View.INVISIBLE);
        joggingDetailsImageOpen.setVisibility(View.INVISIBLE);
        joggingDetailsImageClosed.setVisibility(View.VISIBLE);
    }

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

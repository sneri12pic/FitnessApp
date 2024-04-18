package com.example.fitnessapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class HikingActivity extends AppCompatActivity {
    // Bottom Panel Buttons----------------------
    private Button profile;
    private Button home;
    private Button exercise;
    private Button settings;
    // Arthur Seat--------------------------------
    private Button btnArthurSeat;
    private ImageView arthurSeatDetailsImageOpen;
    private ImageView arthurSeatDetailsImageClosed;
    private ImageView arthurSeatImage;
    private ImageView arthurSeatIcons;
    private TextView arthurSeatDetailsText;
    private RelativeLayout arthurSeatDetailsContainer;
    private boolean isArthurSeatOpen = false;
    // Pentland Hills-----------------------------
    private Button btnPentlandHills;
    private ImageView pentlandHillsDetailsImageOpen;
    private ImageView pentlandHillsDetailsImageClosed;
    private TextView pentlandHillsDetailsText;
    private ImageView pentlandHillsImage;
    private ImageView pentlandHillsIcons;
    private RelativeLayout pentlandHillsDetailsContainer;
    private TextView pentlandHillsDetailsTitle;
    private boolean isPentlandHillsOpen = false;
    // Water Leith-------------------------------
    private Button btnWaterLeith;
    private ImageView waterLeithDetailsImageOpen;
    private ImageView waterLeithDetailsImageClosed;
    private TextView waterLeithDetailsText;
    private ImageView waterLeithImage;
    private ImageView waterLeithHillsIcons;
    private RelativeLayout waterLeithDetailsContainer;
    private TextView waterLeithDetailsTitle;
    // INITIAL MARGIN TOP------------------------
    private final int INITIAL_MARGIN_TOP = 310;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiking);
        //Bottom panel ids
        home = findViewById(R.id.btn_home);
        profile = findViewById(R.id.btn_profile);
        exercise = findViewById(R.id.btn_exercise);
        settings = findViewById(R.id.btn_settings);
        //Arthur Seat Group ids
        btnArthurSeat = findViewById(R.id.btn_arthur_seat);
        arthurSeatDetailsImageOpen = findViewById(R.id.arthur_seat_details_image_opened);
        arthurSeatDetailsImageClosed = findViewById(R.id.arthur_seat_details_image_closed);
        arthurSeatDetailsText = findViewById(R.id.arthur_seat_details_text);
        arthurSeatImage = findViewById(R.id.arthur_seat_image);
        arthurSeatIcons = findViewById(R.id.arthur_seat_icons);
        arthurSeatDetailsContainer = findViewById(R.id.arthur_seat_details_container);
        //Pentland Hills Group ids
        btnPentlandHills = findViewById(R.id.btn_pentland_hills);
        pentlandHillsDetailsImageOpen = findViewById(R.id.pentland_details_image_opened); // pentland
        pentlandHillsDetailsImageClosed = findViewById(R.id.pentland_details_image_closed);
        pentlandHillsDetailsText = findViewById(R.id.pentland_details_text);
        pentlandHillsImage = findViewById(R.id.pentland_image);
        pentlandHillsIcons = findViewById(R.id.pentland_hills_icons);
        pentlandHillsDetailsContainer = findViewById(R.id.pentland_hills_details_container);
        pentlandHillsDetailsTitle = findViewById(R.id.pentland_hills_title);
        //Water Leith Group ids
        btnWaterLeith = findViewById(R.id.btn_water_leith);
        waterLeithDetailsImageOpen = findViewById(R.id.water_leith_details_image_opened);
        waterLeithDetailsImageClosed = findViewById(R.id.water_leith_details_image_closed);
        waterLeithDetailsText = findViewById(R.id.water_leith_details_text);
        waterLeithImage = findViewById(R.id.water_leith_image);
        waterLeithHillsIcons = findViewById(R.id.water_leith_hills_icons);
        waterLeithDetailsContainer = findViewById(R.id.water_leith_details_container);
        waterLeithDetailsTitle = findViewById(R.id.water_leith_hills_title);

        //Bottom Panel Buttons
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HikingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HikingActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        exercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HikingActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HikingActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        //Main Group
        btnArthurSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleArthurSeatDetails();
            }
        });
        btnPentlandHills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePentlandHillsDetails();
            }
        });
        btnWaterLeith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleWaterLeithDetails();
            }
        });
    }
    private void toggleArthurSeatDetails() {
        // Toggle visibility of Arthur Seat details
        if (arthurSeatDetailsContainer.getVisibility() == View.VISIBLE) {
            isArthurSeatOpen = false;
            // If Arthur Seat details are visible, hide them
            arthurSeatImage.setVisibility(View.INVISIBLE);
            arthurSeatIcons.setVisibility(View.VISIBLE);
            arthurSeatDetailsContainer.setVisibility(View.INVISIBLE);
            arthurSeatDetailsImageOpen.setVisibility(View.INVISIBLE);
            arthurSeatDetailsText.setVisibility(View.INVISIBLE);
            arthurSeatDetailsImageClosed.setVisibility(View.VISIBLE);
            // Restore default margins for Pentland Hills and Water Leith tabs
            adjustPentlandHillsMargin(INITIAL_MARGIN_TOP - 50);
            if(isPentlandHillsOpen){
                adjustWaterLeithMargin(INITIAL_MARGIN_TOP + pentlandHillsDetailsContainer.getHeight() - 400);
            }
            else{
                adjustWaterLeithMargin(INITIAL_MARGIN_TOP + 200);
            }
        } else {
            isArthurSeatOpen = true;
            arthurSeatImage.setVisibility(View.VISIBLE);
            arthurSeatIcons.setVisibility(View.VISIBLE);
            arthurSeatDetailsContainer.setVisibility(View.VISIBLE);
            arthurSeatDetailsImageOpen.setVisibility(View.VISIBLE);
            arthurSeatDetailsText.setVisibility(View.VISIBLE);
            arthurSeatDetailsImageClosed.setVisibility(View.INVISIBLE);
            // Adjust margins for Pentland Hills and Water Leith tabs
            adjustPentlandHillsMargin(arthurSeatDetailsContainer.getHeight() + 50);
            if(isPentlandHillsOpen == true){
                adjustWaterLeithMargin(INITIAL_MARGIN_TOP + pentlandHillsDetailsContainer.getHeight() - 200);
            }
            else{
                adjustWaterLeithMargin(arthurSeatDetailsContainer.getHeight() + INITIAL_MARGIN_TOP);
            }
        }
    }
    private void togglePentlandHillsDetails() {
        // Toggle visibility of Pentland Hills details
        if (pentlandHillsDetailsContainer.getVisibility() == View.VISIBLE) {
            isPentlandHillsOpen = false;
            // If Pentland Hills details are visible, hide them
            pentlandHillsImage.setVisibility(View.INVISIBLE);
            pentlandHillsIcons.setVisibility(View.VISIBLE);
            pentlandHillsDetailsContainer.setVisibility(View.INVISIBLE);
            pentlandHillsDetailsImageOpen.setVisibility(View.INVISIBLE);
            pentlandHillsDetailsText.setVisibility(View.INVISIBLE);
            pentlandHillsDetailsImageClosed.setVisibility(View.VISIBLE);
            if(!isArthurSeatOpen){
                adjustWaterLeithMargin(INITIAL_MARGIN_TOP + 200);
            }
            else{
                adjustWaterLeithMargin(INITIAL_MARGIN_TOP + pentlandHillsDetailsContainer.getHeight() - 200);
            }
        } else {
            isPentlandHillsOpen = true;
            // If Pentland Hills details are not visible, show them
            pentlandHillsImage.setVisibility(View.VISIBLE);
            pentlandHillsIcons.setVisibility(View.VISIBLE);
            pentlandHillsDetailsContainer.setVisibility(View.VISIBLE);
            pentlandHillsDetailsImageOpen.setVisibility(View.VISIBLE);
            pentlandHillsDetailsText.setVisibility(View.VISIBLE);
            pentlandHillsDetailsImageClosed.setVisibility(View.INVISIBLE);
            if(isArthurSeatOpen){
                // Adjust margins for Water details
                adjustWaterLeithMargin(arthurSeatDetailsContainer.getHeight() + pentlandHillsDetailsContainer.getHeight() - 300);
            }
            else{
                adjustWaterLeithMargin(pentlandHillsDetailsContainer.getHeight());
            }
        }
    }
    public void toggleWaterLeithDetails() {
        // Toggle visibility of jogging details
        if (waterLeithDetailsContainer.getVisibility() == View.VISIBLE) {
            waterLeithImage.setVisibility(View.INVISIBLE);
            waterLeithHillsIcons.setVisibility(View.VISIBLE);
            waterLeithDetailsContainer.setVisibility(View.INVISIBLE);
            waterLeithDetailsImageOpen.setVisibility(View.INVISIBLE);
            waterLeithDetailsImageClosed.setVisibility(View.VISIBLE);
        } else {
            // If jogging details are not visible, show them
            waterLeithImage.setVisibility(View.VISIBLE);
            waterLeithHillsIcons.setVisibility(View.VISIBLE);
            waterLeithDetailsContainer.setVisibility(View.VISIBLE);
            waterLeithDetailsImageOpen.setVisibility(View.VISIBLE);
            waterLeithDetailsImageClosed.setVisibility(View.INVISIBLE);
        }
    }
    //Adjustment For Images And Containers
    private void adjustPentlandHillsMargin(int marginTop) {
        // Adjust the margins for all elements within the jogging details container
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) pentlandHillsDetailsContainer.getLayoutParams();
        layoutParams.setMargins(0, marginTop - 260, 0, 0);
        pentlandHillsDetailsContainer.setLayoutParams(layoutParams);

        // Adjust the margins for all elements within the jogging details joggingDetailsImageClosed
        RelativeLayout.LayoutParams imageClosedLayoutParams = (RelativeLayout.LayoutParams) pentlandHillsDetailsImageClosed.getLayoutParams();
        imageClosedLayoutParams.setMargins(0, marginTop - 31, 0, 0);
        pentlandHillsDetailsImageClosed.setLayoutParams(imageClosedLayoutParams);

        // Adjust the margins for the jogging title
        RelativeLayout.LayoutParams titleLayoutParams = (RelativeLayout.LayoutParams) pentlandHillsDetailsTitle.getLayoutParams();
        titleLayoutParams.setMargins(45, marginTop, 0, 0);
        pentlandHillsDetailsTitle.setLayoutParams(titleLayoutParams);

        // Adjust the margins for the jogging buttons (if needed)
        RelativeLayout.LayoutParams button1LayoutParams = (RelativeLayout.LayoutParams) btnPentlandHills.getLayoutParams();
        button1LayoutParams.setMargins(0, marginTop, 0, 0);
        btnPentlandHills.setLayoutParams(button1LayoutParams);
    }
    private void adjustWaterLeithMargin(int marginTop) {
        // Adjust the top margin of the Water tab's container
        ViewGroup.MarginLayoutParams waterLayoutParams = (ViewGroup.MarginLayoutParams) waterLeithDetailsContainer.getLayoutParams();
        waterLayoutParams.topMargin = marginTop;
        waterLeithDetailsContainer.setLayoutParams(waterLayoutParams);

        // Adjust the margins for all elements within the jogging details container
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) waterLeithDetailsContainer.getLayoutParams();
        layoutParams.setMargins(0, marginTop - 530, 0, 0);
        waterLeithDetailsContainer.setLayoutParams(layoutParams);

        // Adjust the margins for all elements within the jogging details joggingDetailsImageClosed
        RelativeLayout.LayoutParams imageClosedLayoutParams = (RelativeLayout.LayoutParams) waterLeithDetailsImageClosed.getLayoutParams();
        imageClosedLayoutParams.setMargins(0, marginTop - 31, 0, 0);
        waterLeithDetailsImageClosed.setLayoutParams(imageClosedLayoutParams);

        // Adjust the margins for the jogging title
        RelativeLayout.LayoutParams titleLayoutParams = (RelativeLayout.LayoutParams) waterLeithDetailsTitle.getLayoutParams();
        titleLayoutParams.setMargins(45, marginTop, 0, 0);
        waterLeithDetailsTitle.setLayoutParams(titleLayoutParams);

        // Adjust the margins for the jogging buttons (if needed)
        RelativeLayout.LayoutParams button1LayoutParams = (RelativeLayout.LayoutParams) btnWaterLeith.getLayoutParams();
        button1LayoutParams.setMargins(0, marginTop, 0, 0);
        btnWaterLeith.setLayoutParams(button1LayoutParams);
    }
}

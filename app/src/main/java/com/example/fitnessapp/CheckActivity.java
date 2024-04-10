package com.example.fitnessapp;

public class CheckActivity {


    private String current_activity = "";

    public void setCurrentActivity(String activityName) {
        this.current_activity = activityName;
    }
    public String getCurrentActivity() {
        return current_activity;
    }
}

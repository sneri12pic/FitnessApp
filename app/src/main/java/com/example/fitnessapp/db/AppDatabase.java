package com.example.fitnessapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fitnessapp.dao.UserProfileDao;
import com.example.fitnessapp.model.UserProfile;

@Database(entities = {UserProfile.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserProfileDao userProfileDao();
}

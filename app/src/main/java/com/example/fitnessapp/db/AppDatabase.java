package com.example.fitnessapp.db;

import androidx.room.Database;
import androidx.room.TypeConverters;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fitnessapp.dao.UserProfileDao;
import com.example.fitnessapp.model.UserProfile;

import com.example.fitnessapp.dao.CaloriesTrackerDao;
import com.example.fitnessapp.model.CaloriesTracker;
import com.example.fitnessapp.util.Converters;

import org.jspecify.annotations.NonNull;

@Database(entities = {UserProfile.class, CaloriesTracker.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserProfileDao userProfileDao();
    public abstract CaloriesTrackerDao caloriesTrackerDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Add new column to existing table
            database.execSQL("ALTER TABLE UserProfile ADD COLUMN calorieLimit INTEGER NOT NULL DEFAULT 0");

            // Create new table CaloriesTracker
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS CaloriesTracker (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "userId INTEGER NOT NULL, " +
                            "date INTEGER NOT NULL, " + // or TEXT, depending on how you store date
                            "calories INTEGER NOT NULL, " +
                            "consumedCalories INTEGER NOT NULL, " +
                            "FOREIGN KEY(userId) REFERENCES UserProfile(id) ON DELETE CASCADE)"
            );
        }
    };

}

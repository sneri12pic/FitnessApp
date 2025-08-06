package com.example.fitnessapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.fitnessapp.model.CaloriesTracker;

import java.util.List;

@Dao
public interface CaloriesTrackerDao {

    @Insert
    void insert(CaloriesTracker caloriesTracker);

    @Query("SELECT * FROM calories_tracker WHERE userId = :userId")
    List<CaloriesTracker> getEntriesForUser(int userId);

    @Delete
    void delete(CaloriesTracker entry);
}

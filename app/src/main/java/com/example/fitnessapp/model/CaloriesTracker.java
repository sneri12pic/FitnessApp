package com.example.fitnessapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import java.util.Date;

// Separating CaloriesTracker for a clean database design
// Accessing UserProfile id using @ForeignKey
@Entity(
        tableName = "calories_tracker",
        foreignKeys = @ForeignKey(
                entity = UserProfile.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE
        )
)
public class CaloriesTracker {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(index = true)
    public int userId;
    public Date date;
    // public String foodItem;
    public int calories;
    public int consumedCalories;

}
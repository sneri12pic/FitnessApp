package com.example.fitnessapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessapp.model.UserProfile;

import java.util.List;

@Dao
public interface UserProfileDao {

    // Insert a new profile, replace if the same primary key exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertProfile(UserProfile profile);

    // Get all user profiles
    @Query("SELECT * FROM user_profile LIMIT 1")
    List<UserProfile> getAllProfiles();

    // Get a user profile by ID
    @Query("SELECT * FROM user_profile WHERE id = :id LIMIT 1")
    UserProfile getUserById(int id);

    // Get all profile IDs
    @Query("SELECT id FROM user_profile")
    List<Integer> getProfileIds();

    // Update a profile
    @Update
    void update(UserProfile userProfile);
}

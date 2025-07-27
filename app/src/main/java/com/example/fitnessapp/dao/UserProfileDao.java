package com.example.fitnessapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fitnessapp.model.UserProfile;

@Dao
public interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(UserProfile profile);

    @Query("SELECT * FROM user_profile LIMIT 1")
    UserProfile getProfile();
}

package com.example.assignment3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


//THE CRUD FUNCTIONS FOR THE TABLE DATABASE

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAll(): Flow<List<Workout>>

    @Insert
    fun insert(workout: Workout)

    @Delete
    fun delete(workout: Workout)

    // CLEAR ALL WORKOUTS FROM THE TABLE
    @Query("DELETE FROM workouts")
    suspend fun clearAll()            // SUSPEND FOR COROUTINE USE
}
package com.example.assignment3

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Workout::class], version =1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao() : WorkoutDao
}
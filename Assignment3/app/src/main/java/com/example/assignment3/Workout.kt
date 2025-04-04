package com.example.assignment3

import androidx.room.Entity
import androidx.room.PrimaryKey


//IM USING ROOM DATABASE FOR THIS ASSIGNMENT
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cal: Int,               //calories
    val mins: Int,              //minutes
    val imageResId: Int         //store the image
)

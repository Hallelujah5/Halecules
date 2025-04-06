package com.example.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers  // ADD THIS
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    // DAO SET DIRECTLY BY FRAGMENTS
    lateinit var dao: WorkoutDao
    // LIVE DATA OF WORKOUTS WITH IMAGES
    val workouts: LiveData<List<Workout>> by lazy { dao.getAll().asLiveData() }

    // ADD A NEW WORKOUT WITH IMAGE ON IO THREAD
    fun addWorkout(name: String, cal: Int, mins: Int, imageResId: Int, useCoroutines: Boolean = true) {
        val workout = Workout(name = name, cal = cal, mins = mins, imageResId = imageResId)
        if (useCoroutines) {
            viewModelScope.launch(Dispatchers.IO) {  // VARIANT 2: COROUTINES
                dao.insert(workout)
            }
        } else {
            dao.insert(workout)  // VARIANT 1: MAIN THREAD
        }
    }

    // DELETE A WORKOUT ON IO THREAD
    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {  // SAME FIX HERE
            dao.delete(workout)
        }
    }
}
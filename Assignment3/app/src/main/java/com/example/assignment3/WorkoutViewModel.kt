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
    fun addWorkout(name: String, cal: Int, mins: Int, imageResId: Int) {
        viewModelScope.launch(Dispatchers.IO) {  // SWITCH TO IO DISPATCHER
            val workout = Workout(name = name, cal = cal, mins = mins, imageResId = imageResId)
            dao.insert(workout)  // NOW SAFE ON BACKGROUND THREAD
        }
    }

    // DELETE A WORKOUT ON IO THREAD
    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {  // SAME FIX HERE
            dao.delete(workout)
        }
    }
}
package com.example.assignment3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    // COROUTINE SCOPE FOR BACKGROUND TASKS
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        // CLEAR DATABASE FILE
//        applicationContext.deleteDatabase("Fitness-app-db")


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Fitness-app-db"
        ).build()

        // SEED DATABASE ON FIRST LAUNCH IN BACKGROUND
        if (savedInstanceState == null) {
            coroutineScope.launch {  // RUN ON IO THREAD
                val dao = db.workoutDao()
                dao.clearAll()
                val currentWorkouts = dao.getAll().first()  // SAFE IN COROUTINE
                if (currentWorkouts.isEmpty()) {  // CHECK IF DB IS EMPTY
                    dao.insert(Workout(name = "BENCH PRESS", cal = 100, mins = 10, imageResId = R.drawable.benchpress))
                    dao.insert(Workout(name = "CABLE ROW", cal = 150, mins = 15, imageResId = R.drawable.cablerow))
                    dao.insert(Workout(name = "CABLE PULLDOWN", cal = 200, mins = 20, imageResId = R.drawable.cablepulldown))
                }
            }
        }

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomepageFragment())
                        .commit()
                    true
                }
                R.id.navigation_workout -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, WorkoutFragment())
                        .commit()
                    true
                }
                R.id.navigation_profile -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> {

                    false

            }
        }
        }
        //first time start up then start with homepage
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomepageFragment())
                .commit()
        }

    }
}
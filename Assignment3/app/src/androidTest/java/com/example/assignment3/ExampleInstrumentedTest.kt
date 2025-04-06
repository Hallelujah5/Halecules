package com.example.assignment3

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkoutFragmentTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: WorkoutDao
    private lateinit var viewModel: WorkoutViewModel

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            androidx.test.core.app.ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        dao = db.workoutDao()
        viewModel = WorkoutViewModel()
        viewModel.dao = dao  // SET DAO BEFORE FRAGMENT LAUNCH
    }

    @Test
    fun `test add button navigates to AddExerciseFragment`() {
        val scenario = launchFragmentInContainer<WorkoutFragment>(themeResId = R.style.Theme_Assignment3)
        scenario.onFragment { fragment ->
            fragment.viewModel.dao = dao  // ENSURE DAO IS SET
        }

        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.editName)).check(matches(isDisplayed()))
    }

    @Test
    fun `test delete button shows snackbar`() {
        val scenario = launchFragmentInContainer<WorkoutFragment>(themeResId = R.style.Theme_Assignment3)
        scenario.onFragment { fragment ->
            fragment.viewModel.dao = dao  // ENSURE DAO IS SET
            fragment.setupTestWorkout("BENCH PRESS", 100, 10, R.drawable.placeholder)
        }

        onView(withId(R.id.btnDelete)).perform(click())
        onView(withText("REMOVED BENCH PRESS")).check(matches(isDisplayed()))
    }
}
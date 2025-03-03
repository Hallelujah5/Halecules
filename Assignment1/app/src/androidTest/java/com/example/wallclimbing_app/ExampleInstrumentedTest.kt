package com.example.wallclimbing_app

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testScoreAfterScreenRotation() {
        // Perform a climb action
        onView(withId(R.id.ClimbBtn)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("1")))

        // Rotate screen
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(500) // Wait for rotation to complete

        // Check if score is still 1
        onView(withId(R.id.current_score)).check(matches(withText("1")))

        // Rotate back to portrait
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(500)

        // Score should still be 1
        onView(withId(R.id.current_score)).check(matches(withText("1")))
    }
}

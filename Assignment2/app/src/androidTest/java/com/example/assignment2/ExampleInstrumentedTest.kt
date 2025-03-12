package com.example.assignment2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testInitialItemDisplayed() {
        // Check if the first item's name is displayed
        onView(withId(R.id.Ins_name_txtV))
            .check(matches(withText("Guitar")))
        // Check price
        onView(withId(R.id.Ins_price_txtV))
            .check(matches(withText("49$/m")))
    }

    @Test
    fun testNextButtonCyclesItems() {
        // Initial state: Guitar
        onView(withId(R.id.Ins_name_txtV))
            .check(matches(withText("Guitar")))

        // Click Next button
        onView(withId(R.id.nextBtn))
            .perform(click())

        // Check if it cycles to Drums
        onView(withId(R.id.Ins_name_txtV))
            .check(matches(withText("Drumset")))
    }
    @Test
    fun testBorrowButtonOpensDetails() {
        // Click Borrow button
        onView(withId(R.id.borrowBtn))
            .perform(click())

        // Check if DetailsActivity shows the current item's name
        onView(withId(R.id.Ins_name_txtV))
            .check(matches(withText("Guitar")))
    }

}
package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate profile layout
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val hoursText = view.findViewById<TextView>(R.id.textHours)

        // CALCULATE HOURS
        val semesterStart = LocalDateTime.parse("2025-03-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val now = LocalDateTime.now()
        val hoursPassed = Duration.between(semesterStart, now).toHours()

        // ESTIMATE HOURS SPENT
        //this is assuming the user spends at least 1 hour a day working out, just to add somewhat bit of dynamic time tracking.
        val hoursSpent = hoursPassed / 24
        hoursText.text = "$hoursSpent"
        return view
    }
}
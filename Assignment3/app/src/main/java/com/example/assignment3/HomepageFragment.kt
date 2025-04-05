package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlin.random.Random

class HomepageFragment : Fragment() {
    private val viewModel: WorkoutViewModel by viewModels({ requireActivity() })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        // SET DAO FROM ACTIVITY
        viewModel.dao = (requireActivity() as MainActivity).db.workoutDao()

        //FindVIEWByID
        val recommendedImage = view.findViewById<ImageView>(R.id.recommendedImage)
        val recommendedName = view.findViewById<TextView>(R.id.recommendedName)
        val recommendedDetails = view.findViewById<TextView>(R.id.recommendedDetails)

        //DISPLAY RANDOM RECOMMENDED
        viewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            if (workouts.isNotEmpty()) {
                val randomWorkout = workouts[Random.nextInt(workouts.size)]
                recommendedImage.setImageResource(randomWorkout.imageResId)
                recommendedName.text = randomWorkout.name
                recommendedDetails.text = "${randomWorkout.cal} CAL, ${randomWorkout.mins} MIN"
            } else {
                // FALLBACK IF NO WORKOUTS
                recommendedName.text = "NO WORKOUTS"
                recommendedDetails.text = ""
            }
        }


        return view
    }
}
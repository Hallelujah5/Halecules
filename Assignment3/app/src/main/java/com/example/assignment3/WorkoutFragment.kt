package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutFragment : Fragment() {
    val viewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewWorkouts)
        val addButton = view.findViewById<Button>(R.id.buttonAdd)

        // ONLY SET DAO FROM MAINACTIVITY IF IT'S THE RIGHT TYPE
        if (requireActivity() is MainActivity) {
            viewModel.dao = (requireActivity() as MainActivity).db.workoutDao()
        }  // ELSE ASSUME TEST HAS SET IT

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = WorkoutAdapter(mutableListOf()) { workout ->
            viewModel.deleteWorkout(workout)
        }
        recyclerView.adapter = adapter

        viewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            adapter.updateWorkouts(workouts)
        }

        addButton.setOnClickListener {
            println("ADD BUTTON CLICKED")
            try {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddExerciseFragment())
                    .addToBackStack(null)
                    .commit()
            } catch (e: Exception) {
                println("ADD WORKOUT FAILED: $e")
            }
        }

        //Code for investigation video, testing with/without Coroutine threads
        testBulkInsert(true)
        return view
    }

    private fun testBulkInsert(useCoroutines: Boolean) {
        for (i in 1..500) {
            viewModel.addWorkout("Workout $i", 100, 20, R.drawable.placeholder, useCoroutines)
        }
    }


    fun setupTestWorkout(name: String, cal: Int, mins: Int, imageResId: Int) {
        viewModel.addWorkout(name, cal, mins, imageResId)
    }
}
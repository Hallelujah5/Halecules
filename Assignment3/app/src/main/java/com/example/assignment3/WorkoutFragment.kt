package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class WorkoutFragment : Fragment() {
    //DECLARE VIEWMODEL WITH FACTORY OT CONNECT TO ROOM VIA DAO
    private val viewModel: WorkoutViewModel by activityViewModels()  // ACTIVITY SCOPE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflate the workout layout
        val view = inflater.inflate(R.layout.fragment_workout, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewWorkouts)
        val addButton = view.findViewById<Button>(R.id.buttonAdd)

        //DAO setup
        viewModel.dao = (requireActivity() as MainActivity).db.workoutDao()

        //SET VERTICAL SCROLLING
        recyclerView.layoutManager = LinearLayoutManager(context)
        //CREATE ADAPTER
        val adapter = WorkoutAdapter(mutableListOf()) {workouts -> viewModel.deleteWorkout(workouts)}
        recyclerView.adapter = adapter

        //SETTING UP AN OBSERVER, TO WATCH CHANGES AND UPDATE ADAPTTTT
        viewModel.workouts.observe(viewLifecycleOwner){ workouts -> adapter.updateWorkouts(workouts)}



        // ADD BUTTON TO GO TO ADD SCREEN
        addButton.setOnClickListener {
            println("ADD BUTTON CLICKED")  // DEBUG LOG
            try {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddExerciseFragment())
                    .addToBackStack(null)
                    .commit()
            } catch (e: Exception) {
                println("ADD WORKOUT FAILED: $e")  // CATCH CRASH
            }
        }

        return view
    }
}
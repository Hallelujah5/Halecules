package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels  // SWITCH TO ACTIVITY SCOPE
import com.google.android.material.snackbar.Snackbar

class AddExerciseFragment : Fragment() {
    // SHARE VIEWMODEL WITH WORKOUTFRAGMENT FOR CONSISTENCY
    private val viewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("ADD FRAGMENT STARTING")
        val view = try {
            inflater.inflate(R.layout.fragment_add_exercise, container, false)
        } catch (e: Exception) {
            println("INFLATION FAILED: $e")
            return null
        }
        println("ADD FRAGMENT INFLATED")

        val nameEdit = view.findViewById<EditText>(R.id.editName)
        val calEdit = view.findViewById<EditText>(R.id.editCal)
        val minsEdit = view.findViewById<EditText>(R.id.editMins)
        val saveButton = view.findViewById<Button>(R.id.buttonSave)

        try {
            viewModel.dao = (requireActivity() as MainActivity).db.workoutDao()
            println("DAO SET: ${viewModel.dao}")
        } catch (e: Exception) {
            println("DAO SET FAILED: $e")
        }

        saveButton.setOnClickListener {
            println("SAVE BUTTON CLICKED")
            val name = nameEdit.text.toString()
            val cal = calEdit.text.toString().toIntOrNull() ?: 0
            val mins = minsEdit.text.toString().toIntOrNull() ?: 0
            if (name.isNotEmpty()) {
                try {
                    viewModel.addWorkout(name, cal, mins, R.drawable.placeholder)
                    println("WORKOUT ADDED")
                    // SHOW SNACKBAR BEFORE POPPING BACK
                    Snackbar.make(
                        view,  // ROOT VIEW FOR DISPLAY
                        "ADDED $name",
                        Snackbar.LENGTH_SHORT  // SHORTER SINCE NO UNDO
                    ).show()
                    parentFragmentManager.popBackStack()
                } catch (e: Exception) {
                    println("ADD WORKOUT FAILED: $e")
                }
            }
        }

        return view
    }
}
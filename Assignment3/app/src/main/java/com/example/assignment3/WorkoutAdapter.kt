package com.example.assignment3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class WorkoutAdapter(
    private var workouts: MutableList<Workout>,
    private val onDeleteClick: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageWorkout)
        val nameText: TextView = itemView.findViewById(R.id.textWorkoutName)
        val detailsText: TextView = itemView.findViewById(R.id.textWorkoutDetails)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.imageView.setImageResource(workout.imageResId)
        holder.nameText.text = workout.name
        holder.detailsText.text = "${workout.cal} CAL • ${workout.mins} MINS"
        //delete workout button
        holder.btnDelete.setOnClickListener {
            val removedWorkout = workouts[position]
            workouts.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, workouts.size)



            //SNACKBAR TO NOTIFY users of their deletion choices, also have an UNDO button to undo deleted exercise.
            Snackbar.make(
                holder.itemView,
                "REMOVED ${removedWorkout.name}",
                Snackbar.LENGTH_LONG
            ).setAction("UNDO") {
                workouts.add(position, removedWorkout)
                notifyItemInserted(position)
                notifyItemRangeChanged(position, workouts.size)
            }.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event != DISMISS_EVENT_ACTION) {
                        onDeleteClick(removedWorkout)
                    }
                }
            }).show()
        }
    }

    override fun getItemCount(): Int = workouts.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateWorkouts(newWorkouts: List<Workout>) {  // ACCEPT LIST, CONVERT INTERNALLY
        workouts.clear()  // CLEAR EXISTING
        workouts.addAll(newWorkouts)  // ADD NEW DATA
        notifyDataSetChanged()
    }
}
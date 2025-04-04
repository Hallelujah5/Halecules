package com.example.assignment3

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class WorkoutAdapter(private var workouts: List<Workout>, private val onDeleteClick: (Workout) -> Unit):
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>(){


    //ViewHolder with the images and the texts
    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageWorkout)
        val nameText: TextView = itemView.findViewById(R.id.textWorkoutName)
        val detailsText: TextView = itemView.findViewById(R.id.textWorkoutDetails)
        val btnDelete : Button = itemView.findViewById(R.id.btnDelete)
    }


    //infkate the rows
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }


    //Bind data with images
    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.imageView.setImageResource(workout.imageResId)
        holder.nameText.text = workout.name
        holder.detailsText.text = "${workout.cal} CAL, ${workout.mins} MINS"
        holder.btnDelete.setOnClickListener{
            onDeleteClick(workout)
        }
    }

    override fun getItemCount(): Int = workouts.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateWorkouts(newWorkouts: List<Workout>) {
        workouts = newWorkouts
        notifyDataSetChanged()
    }

}
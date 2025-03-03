package com.example.assignment2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val name: String,
    val price: Int,
    val rating: Float,
    val imageResId: Int,
    val year : Int,
    val radioOptions : List<String>,
    var rented : Boolean = false
) : Parcelable
package com.example.lightmeup

import androidx.lifecycle.ViewModel

class mainViewModel : ViewModel() {
    var currentIcon = 0
    val icons = listOf(
        R.drawable.report,
        R.drawable.checkcircle,
        R.drawable.announcement,
    )

    fun getNextIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}
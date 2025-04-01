package com.example.lightmeup

import androidx.lifecycle.ViewModel
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity() {
    private val viewModel: mainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val iconImageView: ImageView = findViewById(R.id.currentImageView)
        iconImageView.setImageDrawable(AppCompatResources.getDrawable(this, viewModel.icons[viewModel.currentIcon]))

        iconImageView.setOnClickListener {
            val drawableId = viewModel.getNextIcon()
            iconImageView.setImageDrawable(AppCompatResources.getDrawable(this, drawableId))
            Log.d("MainActivity", "Icon changed to: $drawableId")
        }
    }

//    override fun onSaveInstance   State(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("currentIcon", currentIcon)
//    }
}
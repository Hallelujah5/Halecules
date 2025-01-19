package com.example.lightmeup

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var currentImageV : ImageView
    var currentSvg = 0;
    var Svgs = arrayOf(R.drawable.report, R.drawable.checkcircle, R.drawable.announcement)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    currentImageV = findViewById(R.id.currentImageView)
    currentImageV.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.currentImageView->{
                currentSvg = (currentSvg + 1) % Svgs.size
                currentImageV.setImageResource(Svgs[currentSvg])
            }
        }


    }
}
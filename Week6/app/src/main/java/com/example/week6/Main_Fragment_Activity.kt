package com.example.week6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Main_Fragment_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_frag_layout)
    }


    fun showText(firstName: String, lastName: String) {
        val bottomFragment = supportFragmentManager.findFragmentById(R.id.fragment_bottom) as BottomFragment
        bottomFragment.showText(firstName, lastName)
    }
}

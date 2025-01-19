package com.example.cards

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var card: Card
    lateinit var txtView: TextView
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        card = Card("ACE", "HEARTS")
        txtView = findViewById(R.id.textView)
        btn = findViewById(R.id.btnFlip)
        btn.setOnClickListener(this)

        txtView.text = card.printDetails()
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnFlip-> {
                card.flip()
                txtView.text = card.printDetails()
            }
        }
    }
}
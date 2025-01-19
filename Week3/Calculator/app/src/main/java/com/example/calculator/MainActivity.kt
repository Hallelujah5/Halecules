package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


lateinit var btnPlus: Button
lateinit var btnMinus: Button
lateinit var btnMult: Button
lateinit var btnDivide: Button

lateinit var num1: EditText
lateinit var num2: EditText
lateinit var result: TextView



class MainActivity : AppCompatActivity(),  View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        btnPlus= findViewById(R.id.btnPlus)
        btnMinus= findViewById(R.id.btnMinus)
        btnMult= findViewById(R.id.btnMult)
        btnDivide= findViewById(R.id.btnDivide)

        num1= findViewById(R.id.num1)
        num2= findViewById(R.id.num2)
        result= findViewById(R.id.result)

        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnMult.setOnClickListener(this)
        btnDivide.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val txt1Str = num1.text.toString()
        val txt2Str = num2.text.toString()

        if (txt1Str.isEmpty() || txt2Str.isEmpty()) {
            result.text = "Please enter valid numbers."
            return
        }

        val txt1 = txt1Str.toDouble()
        val txt2 = txt2Str.toDouble()

        when(v?.id) {
            R.id.btnPlus -> {
                val res = txt1 + txt2
                result.text = res.toString()
            }

            R.id.btnMinus -> {
                val res = txt1 - txt2
                result.text = res.toString()
            }

            R.id.btnMult -> {
                val res = txt1 * txt2
                result.text = res.toString()
            }

            R.id.btnDivide -> {
                if (txt2 == 0.0) {
                    result.text = "Cannot divide by zero."
                } else {
                    val res = txt1 / txt2
                    result.text = res.toString()
                }
            }
        }
    }



}


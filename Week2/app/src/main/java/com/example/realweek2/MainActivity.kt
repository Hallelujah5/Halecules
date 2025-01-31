package com.example.realweek2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat






class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnPlus: Button
    lateinit var btnMinus: Button
    lateinit var txtName:EditText
    lateinit var txtName2:EditText
    lateinit var txtResult:EditText
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
    txtName= findViewById(R.id.txtName)
    txtName2= findViewById(R.id.txtName2)
    txtResult= findViewById(R.id.txtResult)

    btnPlus.setOnClickListener(this)
    btnMinus.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        var Num1 = txtName.text.toString().toDouble()
        var Num2 = txtName2.text.toString().toDouble()
        when(v?.id){
            R.id.btnPlus->{
                var Result = Num1 + Num2
                txtResult.setText(Result.toString())
            }
            R.id.btnMinus->{
                var Result = Num1 - Num2
                txtResult.setText(Result.toString())
            }

        }
    }
}
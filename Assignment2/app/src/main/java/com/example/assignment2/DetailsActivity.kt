package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    lateinit var backBtn : Button
    lateinit var saveBtn : Button
    lateinit var selectedRadio_txtV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)



    //GET INTENT FROM MAINACTIVITY.KT
    val currentIns = intent.getParcelableExtra<Instrument>("instrument")
    val checkedRadio = intent.getStringExtra("radio")

    backBtn = findViewById(R.id.backBtn)
    saveBtn = findViewById(R.id.saveBtn)

        findViewById<TextView>(R.id.Ins_name_txtV).text = currentIns?.name          //NAME
        currentIns?.imageResId?.let { findViewById<ImageView>(R.id.Ins_ImgView).setImageResource(it) }       //IMAGE
        findViewById<TextView>(R.id.Ins_year_txtV).text = currentIns?.year.toString()       //YEAR

        selectedRadio_txtV = findViewById(R.id.selectedRadio_txtV)
        selectedRadio_txtV.text = checkedRadio.toString()               //DISPLAY CHOICE

        findViewById<TextView>(R.id.Ins_price_txtV).setText(currentIns?.price.toString() + "$/m")        //PRICE

        backBtn.setOnClickListener{
            finish()
        }
        saveBtn.setOnClickListener{


            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }





}
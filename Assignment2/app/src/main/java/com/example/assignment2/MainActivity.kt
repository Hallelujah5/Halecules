package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val REQUEST_CODE = 1
    lateinit var borrowBtn : Button
    lateinit var nextBtn : Button




    private val instruments = listOf(
        Instrument("Guitar", 49, 4f, R.drawable.guitar, 2013, listOf("Electric","Vintage")),
        Instrument("Drumset", 63, 4.5f, R.drawable.drums, 2015, listOf("Acoustic","Steel")),
        Instrument("Piano", 95, 5f, R.drawable.piano,2018, listOf("Digital","Grand")),
        Instrument("Violin", 51, 4f, R.drawable.violin, 2016, listOf("Bass","Modern"))
    )
    var currentIndex = 0;
    lateinit var currentIns : Instrument
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nextBtn = findViewById(R.id.nextBtn)
        borrowBtn = findViewById(R.id.borrowBtn)
        borrowBtn.setOnClickListener(this)
        nextBtn.setOnClickListener(this)

        displayCurrentInstrument()
        Log.d("debug", "Displayed 1st instrument!!!!!!!!!!!!!!!!!!!!!!!!!")

    }




//    DISPLAY THE CURRENT INSTRUMENT'S INFO
    fun displayCurrentInstrument(){
        currentIns = instruments[currentIndex]
        findViewById<TextView>(R.id.Ins_name_txtV).text = currentIns.name           //NAME
        findViewById<ImageView>(R.id.Ins_ImgView).setImageResource(currentIns.imageResId)           //IMAGE
        findViewById<RatingBar>(R.id.Ins_ratingBar).rating = currentIns.rating              //RATING

        findViewById<RadioButton>(R.id.radioBtn1).text = currentIns.radioOptions[0]         //RADIO OPTIONS
        findViewById<RadioButton>(R.id.radioBtn2).text = currentIns.radioOptions[1]

        findViewById<TextView>(R.id.Ins_price_txtV).setText(currentIns.price.toString() + "$/m")        //PRICE
    }




//  ON BUTTON CLICKS
    override fun onClick(v: View?) {

        when(v?.id){

            R.id.borrowBtn->{
                    //CHECKING IF USER CHECKED ANY RADIOS
                val checkedRadio = when (findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId) {
                    R.id.radioBtn1 -> currentIns.radioOptions[0]
                    R.id.radioBtn2 -> currentIns.radioOptions[1]
                    else -> {null}

                }
                if (checkedRadio == null) {
                    Toast.makeText(this, "Please select an option for the " + currentIns.name + ".",Toast.LENGTH_SHORT).show()
                    return
                }
                else {
                    val intent = Intent(this, DetailsActivity::class.java)
                    intent.putExtra("instrument", currentIns)
                    intent.putExtra("radio", checkedRadio)
                    Log.d("debug", "INTENT PUTEXTRA RANNNNNNNNNNNNNNNNNNNNNNNNNNNNNN")

                    startActivityForResult(intent, REQUEST_CODE)    //NAVIGATE TO DETAILS LAYOUT, REQUEST RESULTS
                    Log.d("debug", "START ACTIVITY RANNNNNN")

                }
            }
            //DISPLAYING NEXT INSTRUMENT
            R.id.nextBtn->{
                currentIndex = (currentIndex + 1) % instruments.size //So that it would loop back to 0 ( the first instrument)
                displayCurrentInstrument()
                Log.d("debug", "DISPLAYING NEXT INSTRUMENTTTTTTTTTTTTT")

            }
        }

    }
}
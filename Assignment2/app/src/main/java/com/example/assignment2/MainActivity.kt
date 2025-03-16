package com.example.assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var borrowBtn: Button
    private lateinit var nextBtn: Button

    private var bal = 500

    var instruments = mutableListOf(
        Instrument("Guitar", 140, 4f, R.drawable.guitar, 2013, listOf("Electric", "Vintage")),
        Instrument("Drumset", 205, 4.5f, R.drawable.drums, 2015, listOf("Acoustic", "Steel")),
        Instrument("Piano", 315, 5f, R.drawable.piano, 2018, listOf("Digital", "Grand")),
        Instrument("Violin", 112, 4f, R.drawable.violin, 2016, listOf("Bass", "Modern"))
    )
    private var currentIndex = 0
    private lateinit var currentIns: Instrument

    // FETCH FROM DETAIL ACTIVITY.KT
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode){
            RESULT_OK -> {
                // FETCH UPDATED INSTRUMENT
                var currentBal = result.data?.getIntExtra("balance", 0)
                val returnInstrument = result.data?.extras?.getParcelable("returnInstrument", Instrument::class.java)

                returnInstrument?.let {
                    if (currentBal != null) {
                        bal = currentBal
                    }
                    instruments[currentIndex] = it
                    currentIns =it                 // Update the current instrument with the RETURNED one
                    displayCurrentInstrument() // REFRESH UI
                    updateUI()
                }

                Log.d("debug", "RESULT FETCHED SUCCESSFULLY")
            }
            RESULT_CANCELED -> {
                //Snackbar for cancelling
                val snackbar = Snackbar.make(findViewById(R.id.main), "Cancelled booking.", Snackbar.LENGTH_SHORT)
                snackbar.setAction("Dismiss") {
                    snackbar.dismiss()          //a dismiss button for the Snackbar
                }
                snackbar.show()
                }
        }

    }

    @SuppressLint("NewApi")
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




        // Initialize currentIns before displaying
        currentIns = instruments[currentIndex]
        displayCurrentInstrument()
        updateUI()
        Log.d("debug", "Displayed 1st instrument!!!!!!!!!!!!!!!!!!!!!!!!!")

    }

    // DISPLAY THE CURRENT INSTRUMENT'S INFO
    private fun displayCurrentInstrument() {
        findViewById<TextView>(R.id.Bal_txtV).text = "$" + bal
        findViewById<ImageView>(R.id.Ins_ImgView).setImageResource(currentIns.imageResId) // IMAGE
        findViewById<RatingBar>(R.id.Ins_ratingBar).rating = currentIns.rating // RATING

        findViewById<RadioButton>(R.id.radioBtn1).text = currentIns.radioOptions[0] // RADIO OPTIONS
        findViewById<RadioButton>(R.id.radioBtn2).text = currentIns.radioOptions[1]

        findViewById<TextView>(R.id.Rented_msg_txtV).text =  currentIns.name + " Rented!"

        findViewById<TextView>(R.id.Ins_price_txtV).text = "${currentIns.price}$/" // PRICE
        findViewById<TextView>(R.id.Rented_msg_txtV).visibility =           //SHOW THE "Rented!" MESSAGE IF AN INSTRUMENT IS BORROWED.
            if (currentIns.rented) View.VISIBLE else View.GONE

        if (currentIns.rented) {
            findViewById<TextView>(R.id.Ins_price_txtV).setPaintFlags(findViewById<TextView>(R.id.Ins_price_txtV).paintFlags or Paint.STRIKE_THRU_TEXT_FLAG);
            findViewById<TextView>(R.id.Ins_name_txtV).text = currentIns.name + "\u2713" // PRICE WITH A STRIKE THROUGH TEXT EFFECT
        } else {findViewById<TextView>(R.id.Ins_price_txtV).setPaintFlags(findViewById<TextView>(R.id.Ins_price_txtV).paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv());
            findViewById<TextView>(R.id.Ins_name_txtV).text = currentIns.name // NORMAL PRICE
        }
        if (bal < currentIns.price && !currentIns.rented){        //IF CURRENT BALANCE DOES NOT SUFFICIENT TO BUY THE INSTRUMENT, HIGHLIGHT RED PRICES
            findViewById<TextView>(R.id.Ins_price_txtV).setTextColor(ContextCompat.getColor(this, R.color.red))
        } else {findViewById<TextView>(R.id.Ins_price_txtV).setTextColor(ContextCompat.getColor(this,R.color.text))}

    }


    private fun updateUI() {

        //If an instrument is rented, it cannot be rented again!
        if (currentIns.rented) {
            borrowBtn.isEnabled = false
        } else {
            borrowBtn.isEnabled = true
        }

    }



    // ON BUTTON CLICKS
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.borrowBtn -> {

                // CHECKING IF USER CHECKED ANY RADIOS
                val checkedRadio = when (findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId) {
                    //assign the 'string' value to checkedRadio
                    R.id.radioBtn1 -> currentIns.radioOptions[0]
                    R.id.radioBtn2 -> currentIns.radioOptions[1]
                    else -> null
                }


                //DISPLAY A SNACKBAR IF USER DOESN'T SELECT ANY RADIO
                if (checkedRadio == null) {
                    val snackbar = Snackbar.make(findViewById(R.id.main), "Please select an option for the ${currentIns.name}.", Snackbar.LENGTH_SHORT)
                    snackbar.setAction("Dismiss") {
                        snackbar.dismiss()          //a dismiss button for the Snackbar
                    }
                    snackbar.show()
                    return
                }


                //INTENT
                val intent = Intent(this, DetailsActivity::class.java).apply {
                    putExtra("instrument", currentIns)
                    putExtra("radio", checkedRadio)
                    putExtra("balance", bal)
                }
                Log.d("debug", "INTENT PUTEXTRA RANNNNNNNNNNNNNNNNNNNNNNNNNNNNNN") // DEBUG
                startForResult.launch(intent)
                Log.d("debug", "START ACTIVITY RANNNNNN") // DEBUG
            }

            R.id.nextBtn -> {
                currentIndex = (currentIndex + 1) % instruments.size // Loop back to 0
                currentIns = instruments[currentIndex]
                findViewById<RadioGroup>(R.id.radioGroup).clearCheck()
                displayCurrentInstrument()
                updateUI()
                Log.d("debug", "DISPLAYING NEXT INSTRUMENTTTTTTTTTTTTT") // DEBUG
            }
        }
    }




    //ON INSTANCE STATE METHOD TO SAVE DATA UPON SWITCHING SCREEN ORIENTATION.

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)       //SAVE CURRENT DISPLAYED INSTRUMENT
        outState.putParcelableArrayList("InstrumentList", ArrayList(instruments))   //SAVE (MODIFIED) INSTRUMENT LIST
        outState.putInt("balance", bal)            //SAVE CURRENT BALANCE
        Log.d("debug", "SAVE DATA SUCCESSFULLY.")        //DEBUG
    }

    @SuppressLint("NewApi")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentIndex = savedInstanceState.getInt("currentIndex")    //RESTORE CURRENT DISPLAYED INSTRUMENT
        val restoredInstruments = savedInstanceState.getParcelableArrayList("InstrumentList", Instrument::class.java)
        if (restoredInstruments != null) {
            instruments.clear()
            instruments.addAll(restoredInstruments)         //RESTORE (MODIFIED) INSTRUMENT LIST
        }
        bal = savedInstanceState.getInt("balance", 0)       //RESTORE CURRENT BALANCE
        Log.d("debug", "SCREEN ROTATE. RESTORE DATA SUCCESSFULLY.")     //DEBUG
        currentIns = instruments[currentIndex]
        displayCurrentInstrument()          //DISPLAY ON NEW SCREEN ORIENTATION
        updateUI()
    }

}


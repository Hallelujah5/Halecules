package com.example.assignment2

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    lateinit var backBtn : Button
    lateinit var saveBtn : Button
    lateinit var selectedRadio_txtV : TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)



    //GET INTENT FROM MAIN ACTIVITY.KT

    val currentIns = intent.extras?.getParcelable("instrument", Instrument::class.java)
    val checkedRadio = intent.getStringExtra("radio")
    var bal = intent.getIntExtra("balance", 0)

    backBtn = findViewById(R.id.backBtn)
    saveBtn = findViewById(R.id.saveBtn)



    //RECYCLED CODE FROM MAIN ACTIVITY.KT
    findViewById<TextView>(R.id.bal_txtV).setText("$" + bal)       //BALANCE
    findViewById<TextView>(R.id.Ins_name_txtV).text = currentIns?.name          //NAME
    currentIns?.imageResId?.let { findViewById<ImageView>(R.id.Ins_ImgView).setImageResource(it) }       //IMAGE
    findViewById<TextView>(R.id.Ins_year_txtV).text = currentIns?.year.toString()       //YEAR

    selectedRadio_txtV = findViewById(R.id.selectedRadio_txtV)
    selectedRadio_txtV.text = checkedRadio.toString()               //DISPLAY CHOICE

    findViewById<TextView>(R.id.Ins_price_txtV).text = currentIns?.price.toString() + "$/"        //PRICE

    if (currentIns != null) {
        if (bal < currentIns.price){
            findViewById<TextView>(R.id.Ins_price_txtV).setTextColor(ContextCompat.getColor(this, R.color.red))           //RED COLOR TO INDICATE INSUFFICIENT FUNDS
        } else {findViewById<TextView>(R.id.Ins_price_txtV).setTextColor(ContextCompat.getColor(this, R.color.text))}
    }




    //BUTTON FUNC
    backBtn.setOnClickListener{
        setResult(Activity.RESULT_CANCELED)
        finish() }      //CANCEL ANY CURRENT CHANGES



    saveBtn.setOnClickListener{
        currentIns?.rented = true


        //ERROR CHECKING
        if (bal - currentIns?.price!! > 0) {
            bal -= currentIns.price

            //RETURN UPDATED INSTRUMENT TO MAIN ACTIVITY.KT
            intent.putExtra("returnInstrument",currentIns)
            intent.putExtra("balance",bal)
            setResult(Activity.RESULT_OK, intent)
            finish()        //CLOSE

        }
        else {
            //RE-USED SNACKBAR FROM MAIN
            val snackbar = Snackbar.make(findViewById(R.id.detail_main), "You do not have sufficient funds!", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Dismiss") {
                snackbar.dismiss()          //a dismiss button for the Snackbar
            }
            snackbar.show()
        }


    }


}





}
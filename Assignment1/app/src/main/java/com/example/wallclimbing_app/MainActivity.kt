package com.example.wallclimbing_app

import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var ClimbBtn: Button
    lateinit var FallBtn: Button
    lateinit var ResetBtn: Button
    lateinit var current_score : TextView
    lateinit var langSwitch : Button

    private var currentHold = 0
    private var score = 0
    private var isFallen = false

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ClimbBtn = findViewById(R.id.ClimbBtn)
        FallBtn =  findViewById(R.id.fallBtn)
        ResetBtn =  findViewById(R.id.resetBtn)
        current_score =  findViewById(R.id.current_score)
        langSwitch = findViewById(R.id.langSwitchBtn)

        ClimbBtn.setOnClickListener(this)
        FallBtn.setOnClickListener(this)
        ResetBtn.setOnClickListener(this)
        langSwitch.setOnClickListener(this)

        DisplayScore(current_score)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fallBtn-> {
                if (currentHold >= 1 && currentHold != 9 && !isFallen){
                    score = maxOf (0, score - 3)    //to prevent negative score
                    isFallen = true
                    Log.d("Fallen", "The user has fallen with $score score, hold: $currentHold")
                    DisplayScore(current_score)
                }

            }
            R.id.ClimbBtn-> {
                if (!isFallen && currentHold != 9){
                    currentHold++
                    when (currentHold){
                        in 1..3 -> score+=1
                        in 4..6 -> score+=2
                        in 7..9 -> score+=3
                    }

                    Log.d("Climbed", "Current score is $score, hold: $currentHold")
                    DisplayScore(current_score)

                }
            }
            R.id.resetBtn-> {
                score = 0
                currentHold = 0
                isFallen = false

                Log.d("Reset", "Game resetted.")
                DisplayScore(current_score)

            }

            R.id.langSwitchBtn->{
                val newLang = if (Locale.getDefault().language == "en") "vi" else "en"
                val newLocale = Locale(newLang)
                Locale.setDefault(newLocale)

                val config = resources.configuration
                config.setLocale(newLocale)
                resources.updateConfiguration(config, resources.displayMetrics)

                recreate()
            }



        }
    }


/*      score is 0-18, cannot drop below 0.


        hold 1-3 is Blue    (+1)
        hold 4-6 is Green   (+2)
        hold 7-9 is Red     (+3)

        Fall    (-3)


        Climb:  Cannot climb when:   Reached hold 9
                                     Has fallen

        Fall: Cannot fall when:     Has not reach hold 1
                                    Reached hold 9
        Reset:  Reset score to 0

        Cannot climb backwards

        2 layouts, use saveInstanceState to save score value upon device rotation.

        2 languages, use locale and value-vi/string.xml

        use Log.d sometimes.


 */

    private fun DisplayScore(currentScore:TextView) {
        currentScore.text = score.toString()
        currentScore.setTextColor(
            when (currentHold) {
                in 1..3 -> ContextCompat.getColor(this,R.color.blue)
                in 4..6 -> ContextCompat.getColor(this,R.color.green)
                in 7..9 -> ContextCompat.getColor(this,R.color.red)
                else -> {ContextCompat.getColor(this,R.color.black)}
            }
        )

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", score)
        outState.putInt("hold", currentHold)
        outState.putBoolean("isFallen", isFallen)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        score = savedInstanceState.getInt("score")
        currentHold = savedInstanceState.getInt("hold")
        isFallen = savedInstanceState.getBoolean("isFallen")
        DisplayScore(current_score)
    }


}
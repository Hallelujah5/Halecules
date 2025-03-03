package au.edu.swin.sdmd.w07_numberlist

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NumberFactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_fact)

        val number = intent.getIntExtra("number", 0)
        val fact = when {

            number % 3 == 0 && number % 5 == 0 -> "$number is divisible by both 3 and 5"
            number % 3 == 0 -> "$number is divisible by 3"
            number % 5 == 0 -> "$number is divisible by 5"
            else -> "$number is neither divisible by 3 nor 5"
        }

        findViewById<TextView>(R.id.numberFact).text = fact
        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}
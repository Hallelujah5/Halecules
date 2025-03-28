package com.example.week9

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var txtStudentName : EditText
    lateinit var lsView : ListView
    lateinit var btnInsert : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnInsert = findViewById(R.id.btnInsert)
        txtStudentName = findViewById(R.id.txtStudentName)
        lsView = findViewById(R.id.lsValues)

        val context = this
        var db = DBHelper(context)

        btnInsert.setOnClickListener {
            db.insertData(txtStudentName.text.toString())
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
            var strList = db.readData()
            var arr = ArrayAdapter(this, R.layout.template_layout, strList)
            lsView.adapter = arr


        }

    }
}
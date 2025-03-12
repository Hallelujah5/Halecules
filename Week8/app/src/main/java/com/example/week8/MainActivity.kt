package com.example.week8

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var txtInfo : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val file = File(Environment.getExternalStorageDirectory(),"/Documents/test.txt")
        file.createNewFile()
        file.writeText("Hello External Storage-uwu")



        val file_b = File(Environment.getExternalStorageDirectory(),"Documents/test2.txt").exists()
        if (file_b){
            val file = File(Environment.getExternalStorageDirectory(), "Documents/test2.txt")
            txtInfo.text = file.readText()
        }

        else
            Toast.makeText(this,"Not exists", Toast.LENGTH_SHORT).show()
    }
}
package com.example.week09

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    lateinit var txtDisplay : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtDisplay = findViewById(R.id.txtDisplay)
        callThread()




    }


    suspend fun Thread3(): String {
        Log.d("T1", "Start Thread 3")
        delay(1000)
        Log.d("T1", "Finish Thread 3")
        return "Thread 3"
    }


    suspend fun Thread4(): String {
        Log.d("T1", "Start Thread 4")
        delay(1700)
        Log.d("T1", "Finish Thread 4")
        return "Thread 4"
    }
//
//    fun Thread1(): String {
//        for (i in 0..1000) {
//            Log.d("T1", "Thread 1: " + i.toString())
//        }
//        return "Thread 1"
//    }
//
//    fun Thread2(): String {
//        for (i in 0..1000) {
//            Log.d("T1", "Thread 2: " + i.toString())
//        }
//        return "Thread 2"
//    }


    fun callThread() {
        CoroutineScope(IO).launch {
            var exeTime = measureTimeMillis {

                val job1: Deferred<String> = async {
                    Thread3()
                }

                val job2: Deferred<String> = async {
                    Thread4()
                }

                val result1 = job1.await()
                val result2 = job2.await()
                var res = result1 + result2
                Log.d("T1", "Result:" + res)
            }
                Log.d("T1", "Execute time: " + exeTime.toString())
            }

    }

}



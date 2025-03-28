package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SensorActivity : AppCompatActivity(), SensorEventListener {

    var mSensors: Sensor? = null
    lateinit var txtSensorvalues : TextView
    lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sensor_layout)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = mSensorManager.getSensorList(Sensor.TYPE_ALL)
        Log.v("Sensors","Total sensors:" + deviceSensors.size)
        deviceSensors.forEach{
            Log.v("Sensors","Sensor names:" + it)
        }

    }


    override fun onSensorChanged(event: SensorEvent?) {
        var millibarsOfPressure = event!!.values[0]
        if (event.sensor.type == Sensor.TYPE_LIGHT)
            txtSensorvalues.setText(millibarsOfPressure.toString() + "lx")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
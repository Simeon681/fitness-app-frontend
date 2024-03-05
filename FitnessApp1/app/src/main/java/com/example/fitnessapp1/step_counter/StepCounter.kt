package com.example.fitnessapp1.step_counter

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.fitnessapp1.SharedPreferencesInstance

class StepCounter(private val context: Context) : SensorEventListener {
    private var totalSteps = 0
    private var sensorManager: SensorManager? = null
    val MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 100
    private val sharedPreferencesInstance = SharedPreferencesInstance

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepSensor == null) {
            Toast.makeText(
                context,
                "No step counter sensor detected",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            sensorManager?.registerListener(
                this,
                stepSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(ContentValues.TAG, "Sensor accuracy changed: $accuracy")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            totalSteps = event.values[0].toInt()
            sharedPreferencesInstance.saveSteps(totalSteps)
        }
    }

    fun registerListener() {
        sensorManager?.registerListener(
            this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun unregisterListener() {
        sensorManager?.unregisterListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun isPermissionGranted(): Boolean {
        return context.checkSelfPermission(
            android.Manifest.permission.ACTIVITY_RECOGNITION
        ) != PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
            MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION
        )
    }
}
package com.example.fitnessapp1

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp1.navigation.AppNavHost
import com.example.fitnessapp1.step_counter.StepCounter
import com.example.fitnessapp1.ui.theme.FitnessApp1Theme

class MainActivity : ComponentActivity() {
    private var sharedPreferencesInstance = SharedPreferencesInstance
    private lateinit var stepCounter: StepCounter
//    val scheduler = ScheduledTask

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferencesInstance.initSharedPreferences(this)
        stepCounter = StepCounter(this)

        setContent {
            FitnessApp1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }

        if (stepCounter.isPermissionGranted()) {
            stepCounter.requestPermission()
        }

        sharedPreferencesInstance.saveSteps(sharedPreferencesInstance.getSteps())

//        scheduler.performTask(
//            taskToRun = {
//                sharedPreferencesInstance.saveSteps(0)
//            },
//            hour = 0,
//            minute = 0,
//            second = 0
//        )
    }

    override fun onResume() {
        super.onResume()
        stepCounter.registerListener()
    }

    override fun onPause() {
        super.onPause()
        stepCounter.unregisterListener()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == stepCounter.MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(
                    this,
                    "Activity Recognition permission granted",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Activity Recognition permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

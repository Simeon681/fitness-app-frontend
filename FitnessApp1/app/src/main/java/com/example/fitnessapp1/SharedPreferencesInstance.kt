package com.example.fitnessapp1

import android.content.Context
import android.content.SharedPreferences
import java.util.Calendar

object SharedPreferencesInstance {
    private lateinit var sharedPreferences: SharedPreferences

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("jwt", Context.MODE_PRIVATE)
    }

    fun saveJwtToken(token: String) {
        sharedPreferences
            .edit()
            .putString("jwt_token", token)
            .apply()
    }

    fun getJwtToken(): String? {
        return sharedPreferences.getString("jwt_token", null)
    }

    fun saveSteps(totalSteps: Int) {
        val today = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        sharedPreferences
            .edit()
            .putInt("steps_${today.timeInMillis}", totalSteps)
            .apply()
    }

    fun getSteps(): Int {
        val today = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return sharedPreferences.getInt("steps_${today.timeInMillis}", 0)
    }

    fun clearJwtToken() {
        return sharedPreferences
            .edit()
            .remove("jwt_token")
            .apply()
    }
}

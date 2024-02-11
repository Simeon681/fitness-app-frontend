package com.example.fitnessapp1

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesInstance {
    private lateinit var sharedPreferences: SharedPreferences

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("jwt", Context.MODE_PRIVATE)
    }

    fun saveJwtToken(token: String) {
        return sharedPreferences
            .edit()
            .putString("jwt_token", token)
            .apply()
    }

    fun getJwtToken(): String? {
        return sharedPreferences.getString("jwt_token", null)
    }

    fun clearJwtToken() {
        return sharedPreferences
            .edit()
            .remove("jwt_token")
            .apply()
    }
}

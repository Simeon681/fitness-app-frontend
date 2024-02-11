package com.example.fitnessapp1.resource.request

import android.health.connect.datatypes.MealType

data class MealStatRequest(
    val portion: Float,
    val type: MealType
)

package com.example.fitnessapp1.resource.request

data class MealRequest(
    val name: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float
)

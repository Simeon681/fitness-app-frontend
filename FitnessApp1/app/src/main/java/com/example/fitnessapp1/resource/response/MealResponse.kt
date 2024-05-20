package com.example.fitnessapp1.resource.response

data class MealResponse(
    val id: String,
    val name: String,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float
)

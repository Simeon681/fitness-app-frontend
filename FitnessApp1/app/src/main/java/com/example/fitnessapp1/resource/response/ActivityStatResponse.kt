package com.example.fitnessapp1.resource.response

data class ActivityStatResponse(
    val id: Long,
    val steps: Int,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val water: Float
)

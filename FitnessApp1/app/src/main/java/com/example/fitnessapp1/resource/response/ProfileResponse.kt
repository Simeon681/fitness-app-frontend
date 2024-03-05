package com.example.fitnessapp1.resource.response

data class ProfileResponse(
    val goalCalories: Int,
    val goalProtein: Float,
    val goalCarbs: Float,
    val goalFat: Float,
    val goalWater: Float,
    val goalSteps: Int,
)

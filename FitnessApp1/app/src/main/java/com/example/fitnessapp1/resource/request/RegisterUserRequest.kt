package com.example.fitnessapp1.resource.request

import com.example.fitnessapp1.shared.ActivityLevel
import com.example.fitnessapp1.shared.Gender
import com.example.fitnessapp1.shared.WeightGoal

data class RegisterUserRequest(
    val email: String,
    val username: String,
    val password: String,
    val dateOfBirth: String,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val activityLevel: ActivityLevel,
    val weightGoal: WeightGoal,
    val goalSteps: Int
)

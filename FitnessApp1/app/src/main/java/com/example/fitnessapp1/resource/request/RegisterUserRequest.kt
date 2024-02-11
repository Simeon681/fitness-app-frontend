package com.example.fitnessapp1.resource.request

import com.example.fitnessapp1.shared.Gender
import java.time.LocalDate

data class RegisterUserRequest(
    val username: String,
    val password: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val goalCalories: Int,
    val goalWeight: Float,
    val goalSteps: Int,
    val goalWater: Float
)

package com.example.fitnessapp1.resource.response

import com.example.fitnessapp1.shared.Gender
import java.time.LocalDate

data class ProfileResponse(
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val goalCalories: Int,
    val goalWeight: Float,
    val goalSteps: Int,
    val goalWater: Float
)

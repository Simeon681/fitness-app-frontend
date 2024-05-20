package com.example.fitnessapp1.resource.request

import com.example.fitnessapp1.shared.MealType

data class MealStatRequest(
    val portion: Float,
    val type: MealType?
)

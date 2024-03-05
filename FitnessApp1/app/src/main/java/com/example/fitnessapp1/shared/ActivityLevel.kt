package com.example.fitnessapp1.shared

enum class ActivityLevel(val displayName: String) {
    SEDENTARY("Sedentary(little or no exercise)"),
    LIGHTLY_ACTIVE("Lightly Active(exercise 1-3 days per week)"),
    MODERATELY_ACTIVE("Moderately Active(exercise 3-5 days per week)"),
    VERY_ACTIVE("Very Active(exercise 6-7 days per week)"),
    EXTREMELY_ACTIVE("Extremely Active(very intense exercise daily, or physical job)")
}
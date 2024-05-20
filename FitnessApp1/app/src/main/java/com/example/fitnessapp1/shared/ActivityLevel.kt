package com.example.fitnessapp1.shared

enum class ActivityLevel(val displayName: String, val description: String) {
    SEDENTARY(
        "Sedentary",
        "Little or no exercise."
    ),
    LIGHTLY_ACTIVE(
        "Lightly Active",
        "Exercise 1-3 days per week."
    ),
    MODERATELY_ACTIVE(
        "Moderately Active",
        "Exercise 3-5 days per week."
    ),
    VERY_ACTIVE(
        "Very Active",
        "Exercise 6-7 days per week."
    ),
    EXTREMELY_ACTIVE(
        "Extremely Active",
        "Very intense exercise daily, or physical job."
    )
}
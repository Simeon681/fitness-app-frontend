package com.example.fitnessapp1.state

sealed class MealState {
    object Empty : MealState()
    object Loading : MealState()
    object Success : MealState()
    data class Error(val message: String) : MealState()
}
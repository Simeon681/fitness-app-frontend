package com.example.fitnessapp1.state

sealed class MealStatState {
    object Empty : MealStatState()
    object Loading : MealStatState()
    object Success : MealStatState()
    data class Error(val message: String) : MealStatState()
}
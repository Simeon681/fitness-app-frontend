package com.example.fitnessapp1.state

sealed class ActivityStatState {
    object Empty : ActivityStatState()
    object Loading : ActivityStatState()
    object Success : ActivityStatState()
    data class Error(val message: String) : ActivityStatState()
}
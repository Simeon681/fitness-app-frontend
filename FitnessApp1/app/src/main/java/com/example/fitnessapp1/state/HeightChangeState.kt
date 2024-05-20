package com.example.fitnessapp1.state

sealed class HeightChangeState {
    object Empty : HeightChangeState()
    object Loading : HeightChangeState()
    object Success : HeightChangeState()
    class Error(val message: String) : HeightChangeState()
}
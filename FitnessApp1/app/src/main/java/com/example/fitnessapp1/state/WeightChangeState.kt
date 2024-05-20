package com.example.fitnessapp1.state

sealed class WeightChangeState {
    object Empty : WeightChangeState()
    object Loading : WeightChangeState()
    object Success : WeightChangeState()
    class Error(val message: String) : WeightChangeState()
}
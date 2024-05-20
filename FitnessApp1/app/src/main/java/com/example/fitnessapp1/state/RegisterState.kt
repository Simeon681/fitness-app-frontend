package com.example.fitnessapp1.state

sealed class RegisterState {
    object Empty : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    class Error(val message: String) : RegisterState()
}
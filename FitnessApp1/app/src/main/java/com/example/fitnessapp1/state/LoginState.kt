package com.example.fitnessapp1.state

sealed class LoginState {
    object Empty : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    class Error(val message: String) : LoginState()
}
package com.example.fitnessapp1.state

sealed class ProfileState {
    object Empty : ProfileState()
    object Loading : ProfileState()
    object Success : ProfileState()
    class Error(val message: String) : ProfileState()
}
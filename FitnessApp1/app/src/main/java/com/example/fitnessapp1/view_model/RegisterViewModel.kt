package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.service.AuthService
import com.example.fitnessapp1.shared.ActivityLevel
import com.example.fitnessapp1.shared.Gender
import com.example.fitnessapp1.shared.WeightGoal
import com.example.fitnessapp1.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authService: AuthService
) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Empty)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth: StateFlow<String> = _dateOfBirth

    private val _gender = MutableStateFlow(null as Gender?)
    val gender: StateFlow<Gender?> = _gender

    private val _height = MutableStateFlow(null as Float?)
    val height: StateFlow<Float?> = _height

    private val _weight = MutableStateFlow(null as Float?)
    val weight: StateFlow<Float?> = _weight

    private val _activityLevel = MutableStateFlow(null as ActivityLevel?)
    val activityLevel: StateFlow<ActivityLevel?> = _activityLevel

    private val _weightGoal = MutableStateFlow(null as WeightGoal?)
    val weightGoal: StateFlow<WeightGoal?> = _weightGoal

    private val _goalSteps = MutableStateFlow(null as Int?)
    val goalSteps: StateFlow<Int?> = _goalSteps

    fun register(
        username: String,
        password: String,
        dateOfBirth: String,
        gender: Gender?,
        height: Float?,
        weight: Float?,
        activityLevel: ActivityLevel?,
        weightGoal: WeightGoal?,
        goalSteps: Int?
    ) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val response = authService.register(
                RegisterUserRequest(
                    username,
                    password,
                    dateOfBirth,
                    gender,
                    height,
                    weight,
                    activityLevel,
                    weightGoal,
                    goalSteps
                )
            )

            if (response.isSuccessful) {
                _registerState.value = RegisterState.Success
            } else {
                _registerState.value = RegisterState.Error(response.message())
            }
        }
    }

    fun setUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setDateOfBirth(newDateOfBirth: String) {
        _dateOfBirth.value = newDateOfBirth
    }

    fun setGender(newGender: Gender) {
        _gender.value = newGender
    }

    fun setHeight(newHeight: Float) {
        _height.value = newHeight
    }

    fun setWeight(newWeight: Float) {
        _weight.value = newWeight
    }

    fun setActivityLevel(newActivityLevel: ActivityLevel) {
        _activityLevel.value = newActivityLevel
    }

    fun setWeightGoal(newWeightGoal: WeightGoal) {
        _weightGoal.value = newWeightGoal
    }

    fun setGoalSteps(newGoalSteps: Int) {
        _goalSteps.value = newGoalSteps
    }
}
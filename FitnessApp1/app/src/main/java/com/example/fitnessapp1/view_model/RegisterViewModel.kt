package com.example.fitnessapp1.view_model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.service.AuthService
import com.example.fitnessapp1.shared.Gender
import com.example.fitnessapp1.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class RegisterViewModel(
    private val authService: AuthService
) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Empty)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    @RequiresApi(Build.VERSION_CODES.O)
    private val _dateOfBirth = MutableStateFlow(LocalDate.parse(""))
    @RequiresApi(Build.VERSION_CODES.O)
    val dateOfBirth: StateFlow<LocalDate> = _dateOfBirth

    private val _gender = MutableStateFlow(Gender.MALE)
    val gender: StateFlow<Gender> = _gender

    private val _height = MutableStateFlow(0.0f)
    val height: StateFlow<Float> = _height

    private val _weight = MutableStateFlow(0.0f)
    val weight: StateFlow<Float> = _weight

    private val _goalCalories = MutableStateFlow(0)
    val goalCalories: StateFlow<Int> = _goalCalories

    private val _goalWeight = MutableStateFlow(0.0f)
    val goalWeight: StateFlow<Float> = _goalWeight

    private val _goalSteps = MutableStateFlow(0)
    val goalSteps: StateFlow<Int> = _goalSteps

    private val _goalWater = MutableStateFlow(0.0f)
    val goalWater: StateFlow<Float> = _goalWater

    fun register(
        username: String,
        password: String,
        dateOfBirth: LocalDate,
        gender: Gender,
        height: Float,
        weight: Float,
        goalCalories: Int,
        goalWeight: Float,
        goalSteps: Int,
        goalWater: Float
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
                    goalCalories,
                    goalWeight,
                    goalSteps,
                    goalWater
                )
            )

            if (response.isSuccessful) {
                _registerState.value = RegisterState.Success
            } else {
                _registerState.value = RegisterState.Error(response.message())
            }
        }
    }

    fun saveUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun savePassword(newPassword: String) {
        _password.value = newPassword
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveDateOfBirth(newDateOfBirth: LocalDate) {
        _dateOfBirth.value = newDateOfBirth
    }

    fun saveGender(newGender: Gender) {
        _gender.value = newGender
    }

    fun saveHeight(newHeight: Float) {
        _height.value = newHeight
    }

    fun saveWeight(newWeight: Float) {
        _weight.value = newWeight
    }

    fun saveGoalCalories(newGoalCalories: Int) {
        _goalCalories.value = newGoalCalories
    }

    fun saveGoalWeight(newGoalWeight: Float) {
        _goalWeight.value = newGoalWeight
    }

    fun saveGoalSteps(newGoalSteps: Int) {
        _goalSteps.value = newGoalSteps
    }

    fun saveGoalWater(newGoalWater: Float) {
        _goalWater.value = newGoalWater
    }
}
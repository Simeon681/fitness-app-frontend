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

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth: StateFlow<String> = _dateOfBirth

    private val _gender = MutableStateFlow(Gender.MALE)
    val gender: StateFlow<Gender> = _gender

    private val _height = MutableStateFlow(0.0f)
    val height: StateFlow<Float> = _height

    private val _weight = MutableStateFlow(0.0f)
    val weight: StateFlow<Float> = _weight

    private val _activityLevel = MutableStateFlow(ActivityLevel.SEDENTARY)
    val activityLevel: StateFlow<ActivityLevel> = _activityLevel

    private val _weightGoal = MutableStateFlow(WeightGoal.LOSE)
    val weightGoal: StateFlow<WeightGoal> = _weightGoal

    private val _goalSteps = MutableStateFlow(0)
    val goalSteps: StateFlow<Int> = _goalSteps

    fun register(
        email: String,
        username: String,
        password: String,
        dateOfBirth: String,
        gender: Gender,
        height: Float,
        weight: Float,
        activityLevel: ActivityLevel,
        weightGoal: WeightGoal,
        goalSteps: Int
    ) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            try {
                val response = authService.register(
                    RegisterUserRequest(
                        email,
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
                    _registerState.value = RegisterState.Error("Registration failed!")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("An error occurred!")
            }
        }
    }

    fun saveState(state: RegisterState) {
        _registerState.value = state
    }

    fun saveEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun saveUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun savePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun saveDateOfBirth(newDateOfBirth: String) {
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

    fun saveActivityLevel(newActivityLevel: ActivityLevel) {
        _activityLevel.value = newActivityLevel
    }

    fun saveWeightGoal(newWeightGoal: WeightGoal) {
        _weightGoal.value = newWeightGoal
    }

    fun saveGoalSteps(newGoalSteps: Int) {
        _goalSteps.value = newGoalSteps
    }
}
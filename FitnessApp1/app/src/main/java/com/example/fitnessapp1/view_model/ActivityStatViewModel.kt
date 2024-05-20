package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.service.ActivityStatService
import com.example.fitnessapp1.state.ActivityStatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActivityStatViewModel(
    private val activityStatService: ActivityStatService
) : ViewModel() {
    private val _activityStatState = MutableStateFlow<ActivityStatState>(ActivityStatState.Empty)
    val activityStatState: StateFlow<ActivityStatState> = _activityStatState

    private val _activityStat = MutableStateFlow(ActivityStatResponse("", 0, 0, 0f, 0f, 0f, 0f))
    val activityStat: StateFlow<ActivityStatResponse> = _activityStat

    private val _steps = MutableStateFlow(0)
    val steps: StateFlow<Int> = _steps

    private val _water = MutableStateFlow(0.25f)
    val water: StateFlow<Float> = _water

    fun updateActivityStat(
        steps: Int,
        water: Float
    ) {
        viewModelScope.launch {
            _activityStatState.value = ActivityStatState.Loading

            try {
                val response = activityStatService.update(
                    ActivityStatRequest(
                        steps,
                        water
                    )
                )
                if (response.isSuccessful) {
                    _activityStat.value = response.body()!!
                    _activityStatState.value = ActivityStatState.Success
                } else {
                    _activityStatState.value = ActivityStatState.Error("Failed to update activity stat!")
                }
            } catch (e: Exception) {
                _activityStatState.value = ActivityStatState.Error("An error occurred!")
            }
        }
    }

    fun getActivityStat() {
        viewModelScope.launch {
            _activityStatState.value = ActivityStatState.Loading

            try {
                val response = activityStatService.getActivityStat()
                if (response.isSuccessful) {
                    _activityStat.value = response.body()!!
                    _activityStatState.value = ActivityStatState.Success
                } else {
                    _activityStatState.value = ActivityStatState.Error("Failed to get activity stat!")
                }
            } catch (e: Exception) {
                _activityStatState.value = ActivityStatState.Error("An error occurred!")
            }
        }
    }

    fun saveState(newState: ActivityStatState) {
        _activityStatState.value = newState
    }

    fun saveSteps(newSteps: Int) {
        _steps.value = newSteps
    }

    fun saveWater(newWater: Float) {
        _water.value = newWater
    }
}
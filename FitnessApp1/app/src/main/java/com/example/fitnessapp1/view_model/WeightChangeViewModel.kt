package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.WeightRequest
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import com.example.fitnessapp1.service.WeightChangesService
import com.example.fitnessapp1.state.WeightChangeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeightChangeViewModel(
    private val weightChangesService: WeightChangesService
) : ViewModel() {
    private val _weightChangesState = MutableStateFlow<WeightChangeState>(WeightChangeState.Empty)
    val weightChangesState = _weightChangesState

    private val _changes = MutableStateFlow(null as List<WeightChangeResponse>?)
    val changes: StateFlow<List<WeightChangeResponse>?> = _changes

    private val _weight = MutableStateFlow(0f)
    val weight: StateFlow<Float> = _weight

    fun getChanges() {
        viewModelScope.launch {
            _weightChangesState.value = WeightChangeState.Loading

            try {
                val response = weightChangesService.getWeightChanges()
                if (response.isSuccessful) {
                    _changes.value = response.body()
                    _weightChangesState.value = WeightChangeState.Success
                } else {
                    _weightChangesState.value =
                        WeightChangeState.Error("Failed to get weight changes!")
                }
            } catch (e: Exception) {
                _weightChangesState.value =
                    WeightChangeState.Error("An unexpected error occurred!")
            }
        }
    }

    fun updateWeight(weight: Float) {
        viewModelScope.launch {
            _weightChangesState.value = WeightChangeState.Loading

            try {
                val response = weightChangesService.updateWeight(WeightRequest(weight))
                if (response.isSuccessful) {
                    _weightChangesState.value = WeightChangeState.Success
                } else {
                    _weightChangesState.value =
                        WeightChangeState.Error("Failed to update weight!")
                }
            } catch (e: Exception) {
                _weightChangesState.value =
                    WeightChangeState.Error("An unexpected error occurred!")
            }
        }
    }

    fun saveState(newState: WeightChangeState) {
        _weightChangesState.value = newState
    }

    fun saveWeight(newWeight: Float) {
        _weight.value = newWeight
    }
}
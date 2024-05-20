package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.HeightRequest
import com.example.fitnessapp1.resource.response.HeightChangeResponse
import com.example.fitnessapp1.service.HeightChangeService
import com.example.fitnessapp1.state.HeightChangeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeightChangeViewModel(
    private val heightChangesService: HeightChangeService
) : ViewModel() {
    private val _heightChangesState = MutableStateFlow<HeightChangeState>(HeightChangeState.Empty)
    val heightChangesState = _heightChangesState

    private val _changes = MutableStateFlow(null as List<HeightChangeResponse>?)
    val changes: StateFlow<List<HeightChangeResponse>?> = _changes

    private val _height = MutableStateFlow(0f)
    val height: StateFlow<Float> = _height

    fun getChanges() {
        viewModelScope.launch {
            _heightChangesState.value = HeightChangeState.Loading

            try {
                val response = heightChangesService.getHeightChanges()
                if (response.isSuccessful) {
                    _changes.value = response.body()
                    _heightChangesState.value = HeightChangeState.Success
                } else {
                    _heightChangesState.value =
                        HeightChangeState.Error("Failed to get height changes!")
                }
            } catch (e: Exception) {
                _heightChangesState.value =
                    HeightChangeState.Error("An unexpected error occurred!")
            }
        }
    }

    fun updateHeight(height: Float) {
        viewModelScope.launch {
            _heightChangesState.value = HeightChangeState.Loading

            try {
                val response = heightChangesService.updateHeight(HeightRequest(height))

                if (response.isSuccessful) {
                    _heightChangesState.value = HeightChangeState.Success
                } else {
                    _heightChangesState.value =
                        HeightChangeState.Error("Failed to update height!")
                }
            } catch (e: Exception) {
                _heightChangesState.value =
                    HeightChangeState.Error("An unexpected error occurred!")
            }
        }
    }

    fun saveState(newState: HeightChangeState) {
        _heightChangesState.value = newState
    }

    fun saveHeight(newHeight: Float) {
        _height.value = newHeight
    }
}
package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.service.MealStatService
import com.example.fitnessapp1.shared.MealType
import com.example.fitnessapp1.state.MealStatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealStatViewModel(
    private val mealStatService: MealStatService
) : ViewModel() {
    private val _mealStatState = MutableStateFlow<MealStatState>(MealStatState.Empty)
    val mealStatState: StateFlow<MealStatState> = _mealStatState

    private val _portion = MutableStateFlow(0.0f)
    val portion: StateFlow<Float> = _portion

    private val _type = MutableStateFlow(null as MealType?)
    val type: StateFlow<MealType?> = _type

    private val _mealId = MutableStateFlow("")
    val mealId: StateFlow<String> = _mealId

    private val _active = MutableStateFlow(false)
    val active: StateFlow<Boolean> = _active

    fun createMealStat(
        portion: Float,
        type: MealType?,
        mealId: String,
    ) {
        viewModelScope.launch {
            _mealStatState.value = MealStatState.Loading

            try {
                val response = mealStatService.createMealStat(
                    MealStatRequest(
                        portion = portion,
                        type = type
                    ),
                    mealId
                )

                if (response.isSuccessful) {
                    _mealStatState.value = MealStatState.Success
                } else {
                    _mealStatState.value = MealStatState.Error("Failed to create meal stat!")
                }
            } catch (e: Exception) {
                _mealStatState.value = MealStatState.Error("An error occurred!")
            }
        }
    }

    fun saveState(newState: MealStatState) {
        _mealStatState.value = newState
    }

    fun saveMealId(newMealId: String) {
        _mealId.value = newMealId
    }

    fun savePortion(newPortion: Float) {
        _portion.value = newPortion
    }

    fun saveMealType(newType: MealType) {
        _type.value = newType
    }

    fun saveActive(newActive: Boolean) {
        _active.value = newActive
    }
}
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

    private val _portion = MutableStateFlow(null as Float?)
    val portion: StateFlow<Float?> = _portion

    private val _type = MutableStateFlow(null as MealType?)
    val type: StateFlow<MealType?> = _type

    private val _mealId = MutableStateFlow(null as Long?)
    val mealId: StateFlow<Long?> = _mealId

    private val _active = MutableStateFlow(false)
    val active: StateFlow<Boolean> = _active

    fun createMealStat(
        portion: Float?,
        type: MealType?,
        mealId: Long?,
    ) {
        viewModelScope.launch {
            _mealStatState.value = MealStatState.Loading
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
                _mealStatState.value = MealStatState.Error(response.message())
            }
        }
    }

    fun setMealId(newMealId: Long) {
        _mealId.value = newMealId
    }

    fun setPortion(newPortion: Float) {
        _portion.value = newPortion
    }

    fun setMealType(newType: MealType) {
        _type.value = newType
    }

    fun setActive(newActive: Boolean) {
        _active.value = newActive
    }
}
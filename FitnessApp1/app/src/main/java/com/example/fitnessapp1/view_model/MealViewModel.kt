package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.service.MealService
import com.example.fitnessapp1.state.MealState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel(
    private val mealService: MealService
) : ViewModel() {
    private val _mealsState = MutableStateFlow<MealState>(MealState.Empty)
    val mealsState: StateFlow<MealState> = _mealsState

    private val _mealName = MutableStateFlow("")
    val mealName: StateFlow<String> = _mealName

    private val _meals = MutableStateFlow(null as List<MealResponse>?)
    val meals: StateFlow<List<MealResponse>?> = _meals

    private val _meal = MutableStateFlow(null as MealResponse?)
    val meal: StateFlow<MealResponse?> = _meal

    fun searchMealByName(
        mealName: String
    ) {
        viewModelScope.launch {
            _mealsState.value = MealState.Loading
            val response = mealService.searchMealByName(mealName)
            if (response.isSuccessful) {
                _meals.value = response.body()
                _mealsState.value = MealState.Success
            } else {
                _mealsState.value = MealState.Error(response.message())
                _meals.value = null
            }
        }
    }

    fun getMealById(
        mealId: Long?
    ) {
        viewModelScope.launch {
            _mealsState.value = MealState.Loading
            val response = mealService.getMealById(mealId)
            if (response.isSuccessful) {
                _meal.value = response.body()
                _mealsState.value = MealState.Success
            } else {
                _mealsState.value = MealState.Error(response.message())
                _meal.value = null
            }
        }
    }

    fun setMealName(newMealName: String) {
        _mealName.value = newMealName
    }
}
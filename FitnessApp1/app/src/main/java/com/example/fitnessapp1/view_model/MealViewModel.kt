package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.MealRequest
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

    private val _calories = MutableStateFlow(0)
    val calories: StateFlow<Int> = _calories

    private val _protein = MutableStateFlow(0.0f)
    val protein: StateFlow<Float> = _protein

    private val _carbs = MutableStateFlow(0.0f)
    val carbs: StateFlow<Float> = _carbs

    private val _fat = MutableStateFlow(0.0f)
    val fat: StateFlow<Float> = _fat

    private val _meals = MutableStateFlow(null as List<MealResponse>?)
    val meals: StateFlow<List<MealResponse>?> = _meals

    private val _meal = MutableStateFlow(null as MealResponse?)
    val meal: StateFlow<MealResponse?> = _meal

    fun createMeal(
        mealName: String,
        calories: Int,
        protein: Float,
        carbs: Float,
        fat: Float
    ) {
        viewModelScope.launch {
            _mealsState.value = MealState.Loading

            try {
                val response = mealService.create(
                    MealRequest(
                        mealName,
                        calories,
                        protein,
                        carbs,
                        fat
                    )
                )
                if (response.isSuccessful) {
                    _mealsState.value = MealState.Success
                } else {
                    _mealsState.value = MealState.Error("Failed to create meal!")
                }
            } catch (e: Exception) {
                _mealsState.value = MealState.Error("An error occurred!")
            }
        }
    }

    fun searchMealByName(
        mealName: String
    ) {
        viewModelScope.launch {
            _mealsState.value = MealState.Loading

            try {
                val response = mealService.searchMealByName(mealName)
                if (response.isSuccessful) {
                    _meals.value = response.body()
                    _mealsState.value = MealState.Success
                } else {
                    _mealsState.value = MealState.Error("Failed to search for meals!")
                }
            } catch (e: Exception) {
                _mealsState.value = MealState.Error("An error occurred!")
            }
        }
    }

    fun getMealById(
        mealId: String?
    ) {
        viewModelScope.launch {
            _mealsState.value = MealState.Loading

            try {
                val response = mealService.getMealById(mealId)
                if (response.isSuccessful) {
                    _meal.value = response.body()
                    _mealsState.value = MealState.Success
                } else {
                    _mealsState.value = MealState.Error("Failed to get meal!")
                }
            } catch (e: Exception) {
                _mealsState.value = MealState.Error("An error occurred!")
            }
        }
    }

    fun saveState(newMealState: MealState) {
        _mealsState.value = newMealState
    }

    fun saveMealName(newMealName: String) {
        _mealName.value = newMealName
    }

    fun saveCalories(newCalories: Int) {
        _calories.value = newCalories
    }

    fun saveProtein(newProtein: Float) {
        _protein.value = newProtein
    }

    fun saveCarbs(newCarbs: Float) {
        _carbs.value = newCarbs
    }

    fun saveFat(newFat: Float) {
        _fat.value = newFat
    }
}
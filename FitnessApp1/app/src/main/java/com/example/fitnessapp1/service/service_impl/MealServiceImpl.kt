package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.API.MealAPI
import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.resource.response.TempMealResponse
import com.example.fitnessapp1.service.MealService
import retrofit2.Response

class MealServiceImpl(
    private val mealAPI: MealAPI
) : MealService {
    override suspend fun create(request: MealRequest): Response<TempMealResponse> {
        return mealAPI.createMeal(request)
    }

    override suspend fun update(request: MealRequest, id: String): Response<MealResponse> {
        return mealAPI.updateMeal(request, id)
    }

    override suspend fun searchMealByName(mealName: String): Response<List<MealResponse>> {
        return mealAPI.searchMealByName(mealName)
    }

    override suspend fun getMealById(id: String?): Response<MealResponse> {
        return mealAPI.getMealById(id)
    }

    override suspend fun delete(id: String): Response<Unit> {
        return mealAPI.deleteMeal(id)
    }
}
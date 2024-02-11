package com.example.fitnessapp1.service.serviceImpl

import com.example.fitnessapp1.repository.MealRepository
import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.service.MealService
import retrofit2.Response

class MealServiceImpl(
    private val mealApi: MealRepository
) : MealService {
    override suspend fun create(request: MealRequest): Response<MealResponse> {
        return mealApi.createMeal(request)
    }

    override suspend fun update(request: MealRequest, id: Long): Response<MealResponse> {
        return mealApi.updateMeal(request, id)
    }

//    override suspend fun searchMealByName(mealName: String): Response<List<MealRequest>> {
//        return mealApi.searchMeals(mealName)
//    }

    override suspend fun delete(id: Long): Response<Unit> {
        return mealApi.deleteMeal(id)
    }
}
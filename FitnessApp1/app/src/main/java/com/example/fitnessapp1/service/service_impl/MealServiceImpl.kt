package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.repository.MealRepository
import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.service.MealService
import retrofit2.Response

class MealServiceImpl(
    private val mealRepository: MealRepository
) : MealService {
    override suspend fun create(request: MealRequest): Response<MealResponse> {
        return mealRepository.createMeal(request)
    }

    override suspend fun update(request: MealRequest, id: Long): Response<MealResponse> {
        return mealRepository.updateMeal(request, id)
    }

    override suspend fun searchMealByName(mealName: String): Response<List<MealResponse>> {
        return mealRepository.searchMealByName(mealName)
    }

    override suspend fun getMealById(id: Long?): Response<MealResponse> {
        return mealRepository.getMealById(id)
    }

    override suspend fun delete(id: Long): Response<Unit> {
        return mealRepository.deleteMeal(id)
    }
}
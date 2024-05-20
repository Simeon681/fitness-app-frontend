package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.API.MealStatAPI
import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.resource.response.MealStatResponse
import com.example.fitnessapp1.service.MealStatService
import retrofit2.Response

class MealStatServiceImpl(
    private val mealStatAPI: MealStatAPI
) : MealStatService {
    override suspend fun createMealStat(
        request: MealStatRequest,
        mealId: String
    ): Response<MealStatResponse> {
        return mealStatAPI.createMealStat(request, mealId)
    }

    override suspend fun deleteMealStat(id: String): Response<Unit> {
        return mealStatAPI.deleteMealStat(id)
    }
}
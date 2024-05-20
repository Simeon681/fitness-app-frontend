package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.resource.response.MealStatResponse
import retrofit2.Response

interface MealStatService {
    suspend fun createMealStat(request: MealStatRequest, mealId: String): Response<MealStatResponse>
    suspend fun deleteMealStat(id: String): Response<Unit>
}
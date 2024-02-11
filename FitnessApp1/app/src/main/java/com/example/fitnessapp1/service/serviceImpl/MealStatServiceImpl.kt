package com.example.fitnessapp1.service.serviceImpl

import com.example.fitnessapp1.repository.MealStatRepository
import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.resource.response.MealStatResponse
import com.example.fitnessapp1.service.MealStatService
import retrofit2.Response

class MealStatServiceImpl(
    private val mealStatApi: MealStatRepository
) : MealStatService {
    override suspend fun createMealStat(
        request: MealStatRequest,
        id: Long,
        mealId: Long
    ): Response<MealStatResponse> {
        return mealStatApi.createMealStat(request, id, mealId)
    }

    override suspend fun deleteMealStat(id: Long): Response<Unit> {
        return mealStatApi.deleteMealStat(id)
    }
}
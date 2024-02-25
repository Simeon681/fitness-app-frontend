package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.repository.MealStatRepository
import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.resource.response.MealStatResponse
import com.example.fitnessapp1.service.MealStatService
import retrofit2.Response

class MealStatServiceImpl(
    private val mealStatRepository: MealStatRepository
) : MealStatService {
    override suspend fun createMealStat(
        request: MealStatRequest,
        mealId: Long?
    ): Response<MealStatResponse> {
        return mealStatRepository.createMealStat(request, mealId)
    }

    override suspend fun deleteMealStat(id: Long): Response<Unit> {
        return mealStatRepository.deleteMealStat(id)
    }
}
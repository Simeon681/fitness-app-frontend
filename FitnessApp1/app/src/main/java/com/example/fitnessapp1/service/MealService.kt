package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import retrofit2.Response

interface MealService {
    suspend fun create(request: MealRequest): Response<MealResponse>
    suspend fun update(request: MealRequest, id: Long): Response<MealResponse>
    suspend fun searchMealByName(mealName: String): Response<List<MealResponse>>
    suspend fun getMealById(id: Long?): Response<MealResponse>
    suspend fun delete(id: Long): Response<Unit>
}
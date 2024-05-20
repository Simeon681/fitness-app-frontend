package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.resource.response.TempMealResponse
import retrofit2.Response

interface MealService {
    suspend fun create(request: MealRequest): Response<TempMealResponse>
    suspend fun update(request: MealRequest, id: String): Response<MealResponse>
    suspend fun searchMealByName(mealName: String): Response<List<MealResponse>>
    suspend fun getMealById(id: String?): Response<MealResponse>
    suspend fun delete(id: String): Response<Unit>
}
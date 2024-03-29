package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.MealStatRequest
import com.example.fitnessapp1.resource.response.MealStatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MealStatRepository {
    @POST("/api/v1/meal-stat/create")
    suspend fun createMealStat(
        @Body request: MealStatRequest,
        @Query("mealId") mealId: Long?
    ): Response<MealStatResponse>

    @DELETE("/api/v1/meal-stat/{id}")
    suspend fun deleteMealStat(@Path("id") id: Long): Response<Unit>
}
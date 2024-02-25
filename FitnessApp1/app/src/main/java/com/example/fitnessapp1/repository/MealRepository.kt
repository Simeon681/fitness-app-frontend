package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MealRepository {
    @POST("/api/v1/meal/create")
    suspend fun createMeal(@Body request: MealRequest): Response<MealResponse>

    @GET("/api/v1/meal/search")
    suspend fun searchMealByName(@Query("mealName") mealName: String): Response<List<MealResponse>>

    @PATCH("/api/v1/meal/{id}")
    suspend fun updateMeal(@Body request: MealRequest, @Path("id") id: Long): Response<MealResponse>

    @GET("/api/v1/meal/{id}")
    suspend fun getMealById(@Path("id") id: Long?): Response<MealResponse>

    @DELETE("/api/v1/meal/{id}")
    suspend fun deleteMeal(@Path("id") id: Long): Response<Unit>
}
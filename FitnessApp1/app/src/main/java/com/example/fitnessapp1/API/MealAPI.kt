package com.example.fitnessapp1.API

import com.example.fitnessapp1.resource.request.MealRequest
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.resource.response.TempMealResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MealAPI {
    @POST("/api/v1/meal/create")
    suspend fun createMeal(@Body request: MealRequest): Response<TempMealResponse>

    @GET("/api/v1/meal/search")
    suspend fun searchMealByName(@Query("mealName") mealName: String): Response<List<MealResponse>>

    @PATCH("/api/v1/meal/{id}")
    suspend fun updateMeal(@Body request: MealRequest, @Path("id") id: String): Response<MealResponse>

    @GET("/api/v1/meal/{id}")
    suspend fun getMealById(@Path("id") id: String?): Response<MealResponse>

    @DELETE("/api/v1/meal/{id}")
    suspend fun deleteMeal(@Path("id") id: String): Response<Unit>
}
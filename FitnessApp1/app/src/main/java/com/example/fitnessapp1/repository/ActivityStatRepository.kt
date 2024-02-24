package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ActivityStatRepository {
    @POST("/api/v1/activity-stat/create")
    suspend fun createActivityStat(
        @Body request: ActivityStatRequest
    ): Response<ActivityStatResponse>

    @GET("/api/v1/activity-stat")
    suspend fun getActivityStat(): Response<ActivityStatResponse>

    @PATCH("/api/v1/activity-stat")
    suspend fun updateActivityStat(
        @Body request: ActivityStatRequest
    ): Response<ActivityStatResponse>
}
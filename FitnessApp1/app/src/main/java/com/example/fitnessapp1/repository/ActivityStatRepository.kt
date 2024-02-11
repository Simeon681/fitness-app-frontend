package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ActivityStatRepository {
    @POST("/api/v1/activity-stat/create/{id}")
    suspend fun createActivityStat(
        @Body request: ActivityStatRequest,
        @Path("id") id: Long
    ): Response<ActivityStatResponse>

    @PATCH("/api/v1/activity-stat/{userId}/{id}")
    suspend fun updateActivityStat(
        @Body request: ActivityStatRequest,
        @Path("userId") userId: Long,
        @Path("id") id: Long
    ): Response<ActivityStatResponse>
}
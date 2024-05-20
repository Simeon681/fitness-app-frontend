package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import retrofit2.Response

interface ActivityStatService {
    suspend fun create(request: ActivityStatRequest): Response<ActivityStatResponse>
    suspend fun getActivityStat(): Response<ActivityStatResponse>
    suspend fun update(request: ActivityStatRequest): Response<ActivityStatResponse>
}
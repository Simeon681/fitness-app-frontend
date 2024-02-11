package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import retrofit2.Response

interface ActivityStatService {
    suspend fun create(request: ActivityStatRequest, id: Long): Response<ActivityStatResponse>
    suspend fun update(
        request: ActivityStatRequest,
        userId:Long,
        id: Long
    ): Response<ActivityStatResponse>
}
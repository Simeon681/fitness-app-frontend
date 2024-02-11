package com.example.fitnessapp1.service.serviceImpl

import com.example.fitnessapp1.repository.ActivityStatRepository
import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.service.ActivityStatService
import retrofit2.Response

class ActivityStatServiceImpl(
    private val activityStatApi: ActivityStatRepository
) : ActivityStatService {
    override suspend fun create(
        request: ActivityStatRequest,
        id: Long
    ): Response<ActivityStatResponse> {
        return activityStatApi.createActivityStat(request, id)
    }

    override suspend fun update(
        request: ActivityStatRequest,
        userId: Long,
        id: Long
    ): Response<ActivityStatResponse> {
        return activityStatApi.updateActivityStat(request, userId, id)
    }
}
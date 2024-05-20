package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.API.ActivityStatAPI
import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.service.ActivityStatService
import retrofit2.Response

class ActivityStatServiceImpl(
    private val activityStatAPI: ActivityStatAPI
) : ActivityStatService {
    override suspend fun create(
        request: ActivityStatRequest,
    ): Response<ActivityStatResponse> {
        return activityStatAPI.createActivityStat(request)
    }

    override suspend fun getActivityStat(): Response<ActivityStatResponse> {
        return activityStatAPI.getActivityStat()
    }

    override suspend fun update(
        request: ActivityStatRequest
    ): Response<ActivityStatResponse> {
        return activityStatAPI.updateActivityStat(request)
    }
}
package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.repository.ActivityStatRepository
import com.example.fitnessapp1.resource.request.ActivityStatRequest
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.service.ActivityStatService
import retrofit2.Response

class ActivityStatServiceImpl(
    private val activityStatRepository: ActivityStatRepository
) : ActivityStatService {
    override suspend fun create(
        request: ActivityStatRequest,
    ): Response<ActivityStatResponse> {
        return activityStatRepository.createActivityStat(request)
    }

    override suspend fun getActivityStat(): Response<ActivityStatResponse> {
        return activityStatRepository.getActivityStat()
    }

    override suspend fun update(
        request: ActivityStatRequest
    ): Response<ActivityStatResponse> {
        return activityStatRepository.updateActivityStat(request)
    }
}
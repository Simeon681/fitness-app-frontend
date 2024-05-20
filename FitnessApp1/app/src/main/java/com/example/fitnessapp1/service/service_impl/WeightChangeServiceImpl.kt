package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.API.WeightChangeAPI
import com.example.fitnessapp1.resource.request.WeightRequest
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import com.example.fitnessapp1.service.WeightChangesService
import retrofit2.Response

class WeightChangesServiceImpl(
    private val weightChangesAPI: WeightChangeAPI
) : WeightChangesService {
    override suspend fun updateWeight(weightRequest: WeightRequest): Response<Unit> {
        return weightChangesAPI.updateWeight(weightRequest)
    }

    override suspend fun getWeightChanges(): Response<List<WeightChangeResponse>> {
        return weightChangesAPI.getWeightChanges()
    }
}
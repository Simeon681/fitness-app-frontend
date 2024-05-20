package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.WeightRequest
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import retrofit2.Response

interface WeightChangesService {
    suspend fun updateWeight(weightRequest: WeightRequest): Response<Unit>
    suspend fun getWeightChanges(): Response<List<WeightChangeResponse>>
}
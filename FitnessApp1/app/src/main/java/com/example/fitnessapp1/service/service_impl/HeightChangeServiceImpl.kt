package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.API.HeightChangeAPI
import com.example.fitnessapp1.resource.request.HeightRequest
import com.example.fitnessapp1.resource.response.HeightChangeResponse
import com.example.fitnessapp1.service.HeightChangeService
import retrofit2.Response

class HeightChangeServiceImpl(
    private val heightChangeAPI: HeightChangeAPI
) : HeightChangeService {
    override suspend fun updateHeight(heightRequest: HeightRequest): Response<Unit> {
        return heightChangeAPI.updateHeight(heightRequest)
    }

    override suspend fun getHeightChanges(): Response<List<HeightChangeResponse>> {
        return heightChangeAPI.getHeightChanges()
    }
}
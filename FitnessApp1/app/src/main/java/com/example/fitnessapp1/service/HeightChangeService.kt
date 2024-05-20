package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.HeightRequest
import com.example.fitnessapp1.resource.response.HeightChangeResponse
import retrofit2.Response

interface HeightChangeService {
    suspend fun updateHeight(heightRequest: HeightRequest): Response<Unit>
    suspend fun getHeightChanges(): Response<List<HeightChangeResponse>>
}
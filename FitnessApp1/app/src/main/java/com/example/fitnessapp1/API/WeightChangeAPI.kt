package com.example.fitnessapp1.API

import com.example.fitnessapp1.resource.request.WeightRequest
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface WeightChangeAPI {
    @PATCH("/api/v1/weight-change")
    suspend fun updateWeight(@Body weightRequest: WeightRequest): Response<Unit>

    @GET("/api/v1/weight-change")
    suspend fun getWeightChanges(): Response<List<WeightChangeResponse>>
}
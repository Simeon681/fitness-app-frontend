package com.example.fitnessapp1.API

import com.example.fitnessapp1.resource.request.HeightRequest
import com.example.fitnessapp1.resource.response.HeightChangeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HeightChangeAPI {
    @PATCH("/api/v1/height-change")
    suspend fun updateHeight(@Body heightRequest: HeightRequest): Response<Unit>

    @GET("/api/v1/height-change")
    suspend fun getHeightChanges(): Response<List<HeightChangeResponse>>
}
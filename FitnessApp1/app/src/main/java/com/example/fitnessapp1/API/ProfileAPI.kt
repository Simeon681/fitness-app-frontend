package com.example.fitnessapp1.API

import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ProfileAPI {
    @PATCH("/api/v1/profile")
    suspend fun updateProfile(@Body request: ProfileRequest): Response<ProfileResponse>

    @GET("/api/v1/profile")
    suspend fun getProfile(): Response<ProfileResponse>
}
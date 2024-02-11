package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProfileRepository {
    @PATCH("/api/v1/profile/{id}")
    suspend fun updateProfile(@Body request: ProfileRequest, @Path("id") id: Long): Response<ProfileResponse>

    @GET("/api/v1/profile/{id}")
    suspend fun getProfile(@Path("id") id: Long): Response<ProfileResponse>
}
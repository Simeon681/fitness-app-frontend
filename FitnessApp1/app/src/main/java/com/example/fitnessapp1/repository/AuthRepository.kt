package com.example.fitnessapp1.repository

import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRepository {
    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: RegisterUserRequest): Response<AuthResponse>

    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>
}
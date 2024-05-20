package com.example.fitnessapp1.API

import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthAPI {
    @POST("/api/v1/auth/register")
    suspend fun register(@Body request: RegisterUserRequest): Response<AuthResponse>

    @POST("/api/v1/auth/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("/api/v1/auth/auto-login")
    suspend fun autoLogin(): Response<Unit>

    @DELETE("/api/v1/auth/logout")
    suspend fun logout(): Response<Unit>
}
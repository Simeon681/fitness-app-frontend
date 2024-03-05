package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import retrofit2.Response

interface AuthService {
    suspend fun register(request: RegisterUserRequest): Response<AuthResponse>
    suspend fun login(request: AuthRequest): Response<AuthResponse>
}
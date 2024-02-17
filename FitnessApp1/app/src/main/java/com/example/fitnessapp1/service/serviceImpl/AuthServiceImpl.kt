package com.example.fitnessapp1.service.serviceImpl

import com.example.fitnessapp1.SharedPreferencesInstance
import com.example.fitnessapp1.repository.AuthRepository
import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import com.example.fitnessapp1.service.AuthService
import retrofit2.Response

class AuthServiceImpl(
    private val authApi: AuthRepository,
    private val sharedPreferences: SharedPreferencesInstance
) : AuthService {
    override suspend fun register(request: RegisterUserRequest): Response<AuthResponse> {
        val response = authApi.register(request)
        if (response.isSuccessful) {
            sharedPreferences.saveJwtToken(response.body()!!.token)
        }

        return response
    }

    override suspend fun login(request: AuthRequest): Response<AuthResponse> {
        val response = authApi.login(request)
        if (response.isSuccessful) {
            sharedPreferences.saveJwtToken(response.body()!!.token)
        }

        return response
    }
}
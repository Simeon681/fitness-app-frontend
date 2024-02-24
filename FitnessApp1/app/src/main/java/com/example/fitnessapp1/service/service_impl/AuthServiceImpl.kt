package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.SharedPreferencesInstance
import com.example.fitnessapp1.repository.AuthRepository
import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import com.example.fitnessapp1.service.AuthService
import retrofit2.Response

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferencesInstance
) : AuthService {
    override suspend fun register(request: RegisterUserRequest): Response<AuthResponse> {
        val response = authRepository.register(request)
        if (response.isSuccessful) {
            sharedPreferences.saveJwtToken(response.body()!!.token)
        }

        return response
    }

    override suspend fun login(request: AuthRequest): Response<AuthResponse> {
        val response = authRepository.login(request)
        if (response.isSuccessful) {
            sharedPreferences.saveJwtToken(response.body()!!.token)
        }

        return response
    }
}
package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.SharedPreferencesInstance
import com.example.fitnessapp1.API.AuthAPI
import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.resource.request.RegisterUserRequest
import com.example.fitnessapp1.resource.response.AuthResponse
import com.example.fitnessapp1.service.AuthService
import retrofit2.Response

class AuthServiceImpl(
    private val authAPI: AuthAPI,
    private val sharedPreferences: SharedPreferencesInstance
) : AuthService {
    override suspend fun register(request: RegisterUserRequest): Response<AuthResponse> {
        val response = authAPI.register(request)
        if (response.isSuccessful) {
            response.body()?.let {
                sharedPreferences.saveJwtToken(it.accessToken)
                sharedPreferences.saveRefreshToken(it.refreshToken)
            }
        }

        return response
    }

    override suspend fun login(request: AuthRequest): Response<AuthResponse> {
        val response = authAPI.login(request)
        if (response.isSuccessful) {
            response.body()?.let {
                sharedPreferences.saveJwtToken(it.accessToken)
                sharedPreferences.saveRefreshToken(it.refreshToken)
            }
        }

        return response
    }

    override suspend fun autoLogin(): Response<Unit> {
        return authAPI.autoLogin()
    }

    override suspend fun logout(): Response<Unit> {
        val response = authAPI.logout()
        if (response.isSuccessful) {
            sharedPreferences.clearTokens()
        }

        return response
    }
}
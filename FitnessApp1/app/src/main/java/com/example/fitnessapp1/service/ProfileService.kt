package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import retrofit2.Response

interface ProfileService {
    suspend fun updateProfile(request: ProfileRequest): Response<ProfileResponse>
    suspend fun getProfile(): Response<ProfileResponse>
}
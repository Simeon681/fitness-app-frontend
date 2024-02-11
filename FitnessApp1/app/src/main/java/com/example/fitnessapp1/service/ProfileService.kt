package com.example.fitnessapp1.service

import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import retrofit2.Response

interface ProfileService {
    suspend fun updateProfile(request: ProfileRequest, id: Long): Response<ProfileResponse>
    suspend fun getProfile(id: Long): Response<ProfileResponse>
}
package com.example.fitnessapp1.service.serviceImpl

import com.example.fitnessapp1.repository.ProfileRepository
import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.service.ProfileService
import retrofit2.Response

class ProfileServiceImpl(
    private val profileApi: ProfileRepository
) : ProfileService {
    override suspend fun getProfile(id: Long): Response<ProfileResponse> {
        return profileApi.getProfile(id)
    }

    override suspend fun updateProfile(request: ProfileRequest, id: Long): Response<ProfileResponse> {
        return profileApi.updateProfile(request, id)
    }
}
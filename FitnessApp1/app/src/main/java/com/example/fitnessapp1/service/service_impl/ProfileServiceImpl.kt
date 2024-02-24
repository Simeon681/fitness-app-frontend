package com.example.fitnessapp1.service.service_impl

import com.example.fitnessapp1.repository.ProfileRepository
import com.example.fitnessapp1.resource.request.ProfileRequest
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.service.ProfileService
import retrofit2.Response

class ProfileServiceImpl(
    private val profileRepository: ProfileRepository
) : ProfileService {
    override suspend fun updateProfile(request: ProfileRequest): Response<ProfileResponse> {
        return profileRepository.updateProfile(request)
    }

    override suspend fun getProfile(): Response<ProfileResponse> {
        return profileRepository.getProfile()
    }
}
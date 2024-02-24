package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.service.ProfileService
import com.example.fitnessapp1.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileService: ProfileService
) : ViewModel() {
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Empty)
    val profileState: StateFlow<ProfileState> = _profileState

    private val _profile = MutableStateFlow(null as ProfileResponse?)
    val profile: StateFlow<ProfileResponse?> = _profile

    fun getProfile() {
        viewModelScope.launch {
            _profileState.value = ProfileState.Loading
            val response = profileService.getProfile()
            if (response.isSuccessful) {
                _profile.value = response.body()
                _profileState.value = ProfileState.Success
            } else {
                _profileState.value = ProfileState.Error(response.message())
                _profile.value = null
            }
        }
    }
}
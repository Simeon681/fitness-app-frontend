package com.example.fitnessapp1.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp1.resource.request.AuthRequest
import com.example.fitnessapp1.service.AuthService
import com.example.fitnessapp1.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState:StateFlow<LoginState> = _loginState

    private val _username = MutableStateFlow("")
    val username:StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password:StateFlow<String> = _password

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val response = authService.login(AuthRequest(username, password))
            if (response.isSuccessful) {
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error(response.message())
            }
        }
    }

    fun saveUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun savePassword(newPassword: String) {
        _password.value = newPassword
    }
}

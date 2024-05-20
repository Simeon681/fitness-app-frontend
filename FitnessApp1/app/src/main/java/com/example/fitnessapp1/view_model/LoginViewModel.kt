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

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = authService.login(AuthRequest(email, password))
                if (response.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Log In failed!")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred!")
            }
        }
    }

    fun autoLogin() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = authService.autoLogin()
                if (response.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Auto Log In failed!")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred!")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = authService.logout()
                if (response.isSuccessful) {
                    _loginState.value = LoginState.Success
                } else {
                    _loginState.value = LoginState.Error("Log Out failed!")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred!")
            }
        }
    }

    fun saveState(newState: LoginState) {
        _loginState.value = newState
    }

    fun saveEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun savePassword(newPassword: String) {
        _password.value = newPassword
    }
}

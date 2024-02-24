package com.example.fitnessapp1

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(
    private val sharedPreferences: SharedPreferencesInstance
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val isLoginOrRegistration = isLoginOrRegistrationRequest(request.url.toString())

        val token: String? = if (!isLoginOrRegistration) {
            sharedPreferences.getJwtToken()
        } else {
            null
        }

        val modifiedRequest: Request = request.newBuilder()
            .apply {
                token?.let { header("Authorization", "Bearer $it") }
            }
            .build()

        return chain.proceed(modifiedRequest)
    }

    private fun isLoginOrRegistrationRequest(requestUrl: String): Boolean {
        return requestUrl.contains("/api/v1/auth/register")
                || requestUrl.contains("/api/v1/auth/login")
    }

}
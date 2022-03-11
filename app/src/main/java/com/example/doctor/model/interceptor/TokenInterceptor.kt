package com.example.doctor.model.interceptor

import com.example.doctor.model.memory.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    private val token = SharedPref().readString("token")

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.pathSegments.contains("login")) {
            chain.proceed(request)
        }

        val newRequest = request
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(newRequest)
    }
}
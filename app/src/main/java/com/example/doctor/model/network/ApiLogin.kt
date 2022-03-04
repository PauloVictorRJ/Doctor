package com.example.doctor.model.network

import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiLogin {
    @POST("login")
    fun login(@Body login: LoginRequest): Call<LoginResponse>
}
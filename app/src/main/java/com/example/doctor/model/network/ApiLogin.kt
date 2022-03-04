package com.example.doctor.model.network

import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.factory.RetrofitFactory
import retrofit2.http.*


interface ApiLogin {
    @POST("login")
    suspend fun login(@Body login: LoginRequest): LoginResponse

    @GET("doctor")
    suspend fun doctors(
        @Header("Authorization") authorization: String) : List<Doctors>

    companion object {
        val api: ApiLogin by lazy {
            RetrofitFactory
                .build(true)
                .create(ApiLogin::class.java)
        }
    }
}
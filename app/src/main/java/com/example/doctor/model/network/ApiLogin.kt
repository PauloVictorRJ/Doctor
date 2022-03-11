package com.example.doctor.model.network

import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.factory.GsonFactory
import com.example.doctor.model.factory.OkhttpClientFactory
import com.example.doctor.model.factory.RetrofitFactory
import retrofit2.http.*


interface ApiLogin {
    @POST("login")
    suspend fun login(@Body login: LoginRequest): LoginResponse

    @GET("doctor")
    suspend fun getDoctors(
        @Query("page") pageNumber: Int = 1
    ): DoctorsResponse

    companion object {
        val instance: ApiLogin by lazy {
            RetrofitFactory
                .build(
                    OkhttpClientFactory.build(),
                    GsonFactory.build(),
                    true
                )
                .create(ApiLogin::class.java)
        }
    }
}
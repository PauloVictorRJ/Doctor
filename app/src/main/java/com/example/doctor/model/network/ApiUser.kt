package com.example.doctor.model.network

import com.example.doctor.model.ProfileResponse
import com.example.doctor.model.factory.GsonFactory
import com.example.doctor.model.factory.OkhttpClientFactory
import com.example.doctor.model.factory.RetrofitFactory
import retrofit2.http.GET


interface ApiUser {
    @GET("api")
    suspend fun getProfile(): ProfileResponse

    companion object {
        val instance: ApiUser by lazy {
            RetrofitFactory
                .build(
                    OkhttpClientFactory.build(),
                    GsonFactory.build(),
                    false
                )
                .create(ApiUser::class.java)
        }
    }
}
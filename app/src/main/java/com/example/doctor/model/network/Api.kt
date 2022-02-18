package com.example.doctor.model.network

import com.example.doctor.model.ProfileResponse
import com.example.doctor.model.factory.GsonFactory
import com.example.doctor.model.factory.OkhttpClientFactory
import com.example.doctor.model.factory.RetrofitFactory
import retrofit2.http.GET


interface Api {

    @GET("api")
    suspend fun getProfile(): ProfileResponse

    companion object {
        val instance: Api by lazy {
            RetrofitFactory.build(OkhttpClientFactory.build(), GsonFactory.build())
                .create(Api::class.java)
        }
    }
}
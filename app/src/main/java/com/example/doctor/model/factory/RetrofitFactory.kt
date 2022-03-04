package com.example.doctor.model.factory

import com.example.doctor.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {
    fun build(isLoginApi: Boolean = false): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(if (isLoginApi) BuildConfig.BASE_URL_LOGIN else BuildConfig.BASE_URL_USERS)
            .build()
    }
}
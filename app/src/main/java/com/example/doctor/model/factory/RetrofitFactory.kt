package com.example.doctor.model.factory

import com.example.doctor.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {
    fun build(client: OkHttpClient, gson: Gson, isLoginApi: Boolean = false): Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(if (isLoginApi) BuildConfig.BASE_URL_LOGIN else BuildConfig.BASE_URL_USERS)
            .build()
}
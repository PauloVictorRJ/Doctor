package com.example.doctor.model.factory

import com.example.doctor.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitLogin {
    fun Build(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL_API_DOCTOR)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
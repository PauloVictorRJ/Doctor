package com.example.doctor.model.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.doctor.App

object NetworkConnectionUtils {
    fun isConnected():Boolean{
        val connectivityManager = App.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
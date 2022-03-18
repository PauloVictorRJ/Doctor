package com.example.doctor

import android.app.Application
import android.content.Context
import com.example.doctor.model.factory.DatabaseFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DatabaseFactory.build(this)

        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
    }
}
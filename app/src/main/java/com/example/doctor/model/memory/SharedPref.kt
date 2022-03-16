package com.example.doctor.model.memory

import android.content.Context
import android.content.SharedPreferences
import com.example.doctor.App
import java.lang.IllegalArgumentException


class SharedPref {
    private val sharedPref: SharedPreferences = App
        .appContext?.getSharedPreferences("doctor", Context.MODE_PRIVATE) ?: throw IllegalArgumentException("shared preferences error!")

    fun saveBoolean(id: String, boolean: Boolean) {
        sharedPref.edit()?.putBoolean(id, boolean)?.apply()
    }

    fun readBoolean(id: String): Boolean {
        return sharedPref.getBoolean(id, false)
    }

    fun saveString(id:String, string:String){
        sharedPref.edit()?.putString(id, string)?.apply()
    }

    fun readString(id: String): String {
        return sharedPref.getString(id,"")?:""
    }

    companion object {
        val instance: SharedPref by lazy { SharedPref() }
    }
}
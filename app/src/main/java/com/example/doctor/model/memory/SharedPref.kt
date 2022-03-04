package com.example.doctor.model.memory

import android.content.Context
import androidx.fragment.app.Fragment


class SharedPref(fragment: Fragment) {
    private val sharedPref = fragment.activity?.getPreferences(Context.MODE_PRIVATE)

    fun saveBoolean(id: String, boolean: Boolean) {
        sharedPref?.edit()?.putBoolean(id, boolean)?.apply()
    }

    fun readBoolean(id: String): Boolean? {
        return sharedPref?.getBoolean(id, false)
    }

    fun saveString(id:String, string:String){
        sharedPref?.edit()?.putString(id, string)?.apply()
    }

    fun readString(id: String): String? {
        return sharedPref?.getString(id,null)
    }
}
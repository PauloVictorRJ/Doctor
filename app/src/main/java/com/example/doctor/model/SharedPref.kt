package com.example.doctor.model

import android.content.Context
import androidx.fragment.app.Fragment


class SharedPref(fragment: Fragment) {
    val sharedPref = fragment.activity?.getPreferences(Context.MODE_PRIVATE)

    fun saveBoolean(string: String, boolean: Boolean) {
        sharedPref?.edit()?.putBoolean(string, boolean)?.apply()
    }

    fun readBoolean(string: String): Boolean? {
        return sharedPref?.getBoolean(string, false)
    }
}


package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.network.ApiLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginViewModel() : ViewModel() {
    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _success: MutableLiveData<String> = MutableLiveData()
    val success: LiveData<String>
        get() = _success

    private fun api(): ApiLogin {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dh-digital-doctor-api.herokuapp.com/")
            .build()

        return retrofit.create(ApiLogin::class.java)
    }

    fun login(email: String, password: String) {
        api().login(LoginRequest(email, password))
            .enqueue(
                object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        response.errorBody()?.let {
                            _error.value = true
                        }
                        response.body()?.let {
                            _error.value = false
                            _success.value = "Deu bom -> ${it.token}"
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _error.value = true
                    }
                })
    }
}
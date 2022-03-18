package com.example.doctor.viewmodel

import androidx.lifecycle.*
import com.example.doctor.model.DataResult
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.memory.SharedPref
import com.example.doctor.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: LoginRepository = LoginRepository.instance,
    private val sharedPref: SharedPref = SharedPref.instance
) : ViewModel() {

    fun login(email: String, password: String) :LiveData<DataResult<LoginResponse>> =
        repository.login(email, password).asLiveData()

    fun saveToken(token: String) {
        sharedPref.saveString("token", token)
    }

}
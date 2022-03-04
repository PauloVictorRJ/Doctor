package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginViewModel(val repository: LoginRepository = LoginRepository.instance) : ViewModel() {
    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _success: MutableLiveData<String> = MutableLiveData()
    val success: LiveData<String>
        get() = _success

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository
            .login(email, password)
            .catch { _error.postValue(true) }
            .collect { _success.postValue(it.token) }
    }
}
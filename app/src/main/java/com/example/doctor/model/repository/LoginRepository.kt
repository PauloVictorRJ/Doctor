package com.example.doctor.model.repository

import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.network.ApiLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class LoginRepository(private val api: ApiLogin = ApiLogin.instance) {
    fun login(email: String, password: String): Flow<LoginResponse> = flow {
        emit(api.login(LoginRequest(email, password)))
    }.flowOn(Dispatchers.IO)

    companion object {
        val instance: LoginRepository by lazy { LoginRepository() }
    }
}
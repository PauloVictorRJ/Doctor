package com.example.doctor.model.repository

import com.example.doctor.model.DataResult
import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.memory.SharedPref
import com.example.doctor.model.network.ApiLogin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class LoginRepository(
    private val api: ApiLogin = ApiLogin.instance,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val sharedPref: SharedPref = SharedPref.instance
) {
    fun login(email: String, password: String): Flow<DataResult<LoginResponse>> =
        flow<DataResult<LoginResponse>> {
            emit(DataResult.Success(api.login(LoginRequest(email, password))))
        }.catch { e -> emit(DataResult.Error(e)) }
            .onStart { emit(DataResult.Loading(true)) }
            .onEach {
                if (it is DataResult.Success) {
                    sharedPref.saveString("token", it.data.token)
                }
            }
            .onCompletion { emit(DataResult.Loading(false)) }
            .flowOn(defaultDispatcher)

    companion object {
        val instance: LoginRepository by lazy { LoginRepository() }
    }
}
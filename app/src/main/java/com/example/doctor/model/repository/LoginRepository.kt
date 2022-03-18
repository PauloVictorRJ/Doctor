package com.example.doctor.model.repository

import com.example.doctor.model.DataResult
import com.example.doctor.model.LoginRequest
import com.example.doctor.model.LoginResponse
import com.example.doctor.model.network.ApiLogin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class LoginRepository(
    private val api: ApiLogin = ApiLogin.instance,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun login(email: String, password: String): Flow<DataResult<LoginResponse>> =
        flow<DataResult<LoginResponse>> {
            emit(DataResult.Success(api.login(LoginRequest(email, password))))
        }.catch { e -> emit(DataResult.Error(e)) }
            .onStart { emit(DataResult.Loading(true)) }
            .onCompletion { emit(DataResult.Loading(false)) }
            .flowOn(defaultDispatcher)

    companion object {
        val instance: LoginRepository by lazy { LoginRepository() }
    }
}


//class LoginRepository(private val api: ApiLogin = ApiLogin.instance) {
//    fun login(email: String, password: String): Flow<LoginResponse> = flow {
//        emit(api.login(LoginRequest(email, password)))
//    }.flowOn(Dispatchers.IO)
//
//    companion object {
//        val instance: LoginRepository by lazy { LoginRepository() }
//    }
//}
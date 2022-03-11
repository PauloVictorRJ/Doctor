package com.example.doctor.model.repository

import com.example.doctor.model.ProfileResponse
import com.example.doctor.model.network.ApiUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class UserRepository(private val api: ApiUser = ApiUser.instance) {
    fun fecthProfile(): Flow<ProfileResponse> = flow {
        emit(api.getProfile())
    }.flowOn(Dispatchers.IO)

    companion object {
        val instance by lazy {
            UserRepository()
        }
    }
}
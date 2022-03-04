package com.example.doctor.model.repository

import com.example.doctor.model.Doctor
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.network.ApiLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DoctorRepository(private val api: ApiLogin = ApiLogin.api) {
    fun getListDoctors(token: String): Flow<List<Doctor>> = flow {
        emit(api.getDoctors(token))
    }.flowOn(Dispatchers.IO)

    companion object {
        val instance: DoctorRepository by lazy { DoctorRepository() }
    }
}

package com.example.doctor.model.repository

import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.network.ApiLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DoctorRepository(private val api: ApiLogin = ApiLogin.instance) {
    fun getListDoctors(pageNumber: Int): Flow<DoctorsResponse> = flow {
        emit(api.getDoctors(pageNumber))
    }.flowOn(Dispatchers.IO)

    companion object {
        val instance: DoctorRepository by lazy { DoctorRepository() }
    }
}

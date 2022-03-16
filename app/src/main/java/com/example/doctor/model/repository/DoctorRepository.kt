package com.example.doctor.model.repository

import com.example.doctor.App
import com.example.doctor.model.Doctors
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.factory.DatabaseFactory
import com.example.doctor.model.network.ApiLogin
import com.example.doctor.model.network.NetworkConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException


class DoctorRepository(
    private val api: ApiLogin = ApiLogin.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getListDoctors(pageNumber: Int): Flow<DoctorsResponse> = flow {
        emit(api.getDoctors(pageNumber))
    }.catch { error ->
        if (error is IOException && NetworkConnectionUtils.isConnected().not()) {
            val dados = DatabaseFactory.getDatabase(App.appContext!!)
            emit(
                DoctorsResponse(
                    limitPage = dados.doctorDao().listAll().size / 10,
                    results = dados.doctorDao().listAll().map {
                        Doctors(it)
                    })
            )
        }
    }
        .flowOn(dispatcher)


    companion object {
        val instance: DoctorRepository by lazy { DoctorRepository() }
    }
}

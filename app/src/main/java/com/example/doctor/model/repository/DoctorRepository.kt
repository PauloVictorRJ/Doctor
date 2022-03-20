package com.example.doctor.model.repository

import com.example.doctor.model.DataResult
import com.example.doctor.model.Doctors
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.factory.DatabaseFactory
import com.example.doctor.model.network.ApiLogin
import com.example.doctor.model.network.NetworkConnectionUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException


class DoctorRepository(
    private val api: ApiLogin = ApiLogin.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getListDoctors(pageNumber: Int): Flow<DataResult<DoctorsResponse>> =
        flow<DataResult<DoctorsResponse>> {
            emit(DataResult.Success(api.getDoctors(pageNumber)))
        }
            .onStart { emit(DataResult.Loading(true)) }
            .catch { error ->
            if (error is IOException && NetworkConnectionUtils.isConnected().not()) {
                val dados = DatabaseFactory.getDatabase()
                emit(
                    DataResult.Success(
                        DoctorsResponse(
                            limitPage = dados.doctorDao().listAll().size / 10,
                            results = dados.doctorDao().listAll().map {
                                Doctors(it)
                            })
                    )
                )
            }
        }
            .flowOn(dispatcher)


    companion object {
        val instance: DoctorRepository by lazy { DoctorRepository() }
    }
}

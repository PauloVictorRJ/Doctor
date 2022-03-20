package com.example.doctor.viewmodel

import androidx.lifecycle.*
import com.example.doctor.model.DataResult
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.repository.DoctorRepository


class FindDoctorViewModel(
    private val repository: DoctorRepository = DoctorRepository.instance
) :
    ViewModel() {

    fun getDoctorList(pageNumber: Int): LiveData<DataResult<DoctorsResponse>> =
        repository.getListDoctors(pageNumber).asLiveData()
}
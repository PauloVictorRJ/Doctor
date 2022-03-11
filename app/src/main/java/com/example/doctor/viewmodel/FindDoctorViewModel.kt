package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class FindDoctorViewModel(
    private val repository: DoctorRepository = DoctorRepository.instance
) :
    ViewModel() {

    var pageDoctorsLimit = 0

    private val _listDoctors: MutableLiveData<DoctorsResponse> = MutableLiveData()
    val listDoctors: LiveData<DoctorsResponse>
        get() = _listDoctors

    fun getDoctorList(pageNumber: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository
            .getListDoctors(pageNumber)
            .onStart { }
            .catch { }
            .onCompletion { }
            .collect {
                pageDoctorsLimit = it.limite_paginas
                _listDoctors.postValue(it)
            }
    }
}
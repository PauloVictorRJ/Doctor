package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.local.AppDatabase
import com.example.doctor.model.repository.DoctorRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class FindDoctorViewModel(
    private val repository: DoctorRepository = DoctorRepository.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) :
    ViewModel() {

    private val _doctorLimitPage: MutableLiveData<Int> = MutableLiveData(0)
    val doctorLimitPage: LiveData<Int> = _doctorLimitPage

    private val _listDoctors: MutableLiveData<DoctorsResponse> = MutableLiveData()
    val listDoctors: LiveData<DoctorsResponse>
        get() = _listDoctors

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean>
        get() = _error

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getDoctorList(pageNumber: Int) = viewModelScope.launch(dispatcher) {
        repository
            .getListDoctors(pageNumber)
            .onStart { _loading.value = true }
            .catch { _error.value = true }
            .onCompletion { _loading.value = false }
            .collect {
                _doctorLimitPage.value = it.limitPage
                _listDoctors.postValue(it)
            }
    }
}
package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.model.Doctor
import com.example.doctor.model.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class FindDoctorViewModel(val repository: DoctorRepository = DoctorRepository.instance) :
    ViewModel() {

    private val _listDoctors: MutableLiveData<List<Doctor>> = MutableLiveData()
    val listDoctors: LiveData<List<Doctor>>
        get() = _listDoctors

    fun getDoctorList(token: String) = viewModelScope.launch(Dispatchers.IO) {
        repository
            .getListDoctors(token)
            .onStart { }
            .catch { }
            .onCompletion { }
            .collect { _listDoctors.postValue(it)
            var teste = it
            var teste2 = teste}
    }
}
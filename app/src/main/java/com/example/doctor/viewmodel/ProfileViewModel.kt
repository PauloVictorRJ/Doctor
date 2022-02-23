package com.example.doctor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.model.ProfileItem
import com.example.doctor.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class ProfileViewModel(private val userRepository: UserRepository = UserRepository.instance) :
    ViewModel() {

    private val _profile = MutableLiveData<ProfileItem>()
    val profile: LiveData<ProfileItem>
        get() = _profile

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun loadDoctor() {

            viewModelScope.launch(Dispatchers.IO) {
                userRepository.fecthProfile()
                    .onStart {
                        _loading.postValue(true)
                        delay(5000)
                    }
                    .catch { _error.postValue(true) }
                    .onCompletion { _loading.postValue(false) }
                    .collect {
                        _profile.postValue(it.results.first())
                    }
            }
        }
    }

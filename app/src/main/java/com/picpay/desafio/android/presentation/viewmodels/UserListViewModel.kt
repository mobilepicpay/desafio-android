package com.picpay.desafio.android.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity
import com.picpay.desafio.android.domain.usecases.GetLocalUsersUseCase
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import com.picpay.desafio.android.presentation.common.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
    private val getUsersLocal: GetLocalUsersUseCase,
    private val getUsersRemote: GetRemoteUsersUseCase,
) : ViewModel() {

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _users = MutableLiveData<List<UserEntity>>()
    val users = _users as LiveData<List<UserEntity>>

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error


    fun getUsers() {
        setLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsersRemote()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Result.Success -> {
                        _users.value = response.data
                        setLoading(false)
                    }
                    is Result.Error -> getCachedUsers()
                }
            }
        }
    }

    private fun getCachedUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsersLocal()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Result.Success -> {
                        _users.value = response.data
                        _error.value = Event("Loaded local users")
                        setLoading(false)
                    }
                    is Result.Error -> {
                        _error.value = Event(response.exception.message ?: "Unknow Exception")
                        setLoading(false)
                    }
                }
            }
        }
    }

    private fun setLoading(value: Boolean) = _dataLoading.postValue(value)
}
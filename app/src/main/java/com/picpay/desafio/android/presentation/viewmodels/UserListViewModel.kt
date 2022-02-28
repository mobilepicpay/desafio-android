package com.picpay.desafio.android.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.common.Result
import com.picpay.desafio.android.domain.entities.UserEntity
import com.picpay.desafio.android.domain.usecases.GetLocalUsersUseCase
import com.picpay.desafio.android.domain.usecases.GetRemoteUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(
//    private val getUsersLocal: GetLocalUsersUseCase,
    private val getUsersRemote: GetRemoteUsersUseCase,
) : ViewModel() {

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _users: MutableLiveData<List<UserEntity>> by lazy {
        MutableLiveData<List<UserEntity>>().also { getUsers() }
    }
    val users = _users as LiveData<List<UserEntity>>

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun getUsers() {
        setLoading(true)

        CoroutineScope(Dispatchers.IO).launch {
            val response = getUsersRemote()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Result.Success -> {
                        _users.value = response.data
                        setLoading(false)
                    }
                    is Result.Error -> {
                        _error.value = response.exception.message
                        setLoading(false)
                    }
                }
            }
        }
    }

    private fun setLoading(value: Boolean) = _dataLoading.postValue(value)
}
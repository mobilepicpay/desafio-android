package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    val listUsers = MutableLiveData<List<UserDTO>>()

    fun getLIstUsers() {
        viewModelScope.launch {
            runCatching {
                repository.getUsers()
            }.onSuccess {
                listUsers.postValue(it)
            }.onFailure {

            }
        }
    }
}
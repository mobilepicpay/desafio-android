package com.picpay.desafio.android.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.extension.updateAsync
import com.picpay.desafio.android.user.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {

    private val _state = MutableLiveData(UserViewState())
    val state: LiveData<UserViewState>
        get() = _state

    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers {
                _state.updateAsync { postSuccess(it) }
            }
        }
    }
}
package com.picpay.desafio.android.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.usecase.UserUseCase
import com.picpay.desafio.android.user.viewmodel.status.UserStatus
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: UserUseCase) : ViewModel() {
    private val _state: MutableLiveData<UserStatus> = MutableLiveData()
    val state: LiveData<UserStatus> = _state

    fun fetch() {
        viewModelScope.launch {
            interpret(useCase.getUsers())
        }
    }

    private fun interpret(result: Result<List<User>>) {
        if (result.isSuccess) {
            result.getOrNull()?.let {
                _state.postValue(UserStatus.UserSuccess(it))
            }
        } else {
            result.exceptionOrNull()?.let {
                _state.postValue(UserStatus.UserError(it))
            }
        }
    }
}
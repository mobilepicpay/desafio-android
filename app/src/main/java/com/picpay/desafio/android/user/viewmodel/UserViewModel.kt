package com.picpay.desafio.android.user.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.usecase.UserUseCase
import com.picpay.desafio.android.user.viewmodel.events.UserViewEvents
import com.picpay.desafio.android.user.viewmodel.status.UserStatus
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: UserUseCase) : ViewModel() {
    private val _state: MutableLiveData<UserStatus> = MutableLiveData()
    val state: LiveData<UserStatus> = _state
    private val _event: MutableLiveData<UserViewEvents> = MutableLiveData()
    val event: LiveData<UserViewEvents> = _event

    fun start() {
        viewModelScope.launch {
            _event.postValue(UserViewEvents.UserViewShowLoading(View.VISIBLE))
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
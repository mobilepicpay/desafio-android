package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.result.ResultWrapper
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.state.UserState
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: UserUseCase) : ViewModel() {
    private val _state: MutableLiveData<UserState> = MutableLiveData()
    val state: LiveData<UserState> = _state

    fun getUsers(isConnected: Boolean) {
        if (state.value == null || state.value is UserState.Error) {
            _state.value = UserState.ShowLoading(true)
            viewModelScope.launch {
                when (val result = useCase.getUsers(isConnected)) {
                    is ResultWrapper.Error -> {
                        _state.value = UserState.ShowLoading(false)
                        _state.value = UserState.Error
                    }

                    is ResultWrapper.Success -> {
                        _state.value = UserState.ShowLoading(false)
                        _state.value = UserState.ShowUserList(result.value)
                    }
                }
            }
        }
    }
}
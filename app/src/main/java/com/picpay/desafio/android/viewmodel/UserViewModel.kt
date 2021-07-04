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

    fun getUsers(isConnected: Boolean, forceUpdate: Boolean = false) {
        if (!isConnected && forceUpdate)
            _state.value = UserState.Error(ERROR_UPDATE_WITHOUT_INTERNET)

        if ((state.value == null || state.value is UserState.Error) || forceUpdate) {
            _state.value = UserState.ShowLoading(true)
            viewModelScope.launch {
                when (val result = useCase.getUsers(isConnected)) {
                    is ResultWrapper.Error -> {
                        _state.value = UserState.ShowLoading(false)
                        _state.value = UserState.Error(null)
                    }

                    is ResultWrapper.Success -> {
                        _state.value = UserState.ShowLoading(false)
                        _state.value = UserState.ShowUserList(result.value)
                    }
                }
            }
        }
    }

    companion object {
        private const val ERROR_UPDATE_WITHOUT_INTERNET =
            "Erro ao atualizar remotamente, verifique sua conexão."
    }
}
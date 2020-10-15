package com.picpay.desafio.android.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.usecase.UserUseCase
import com.picpay.desafio.android.user.viewmodel.state.UserState
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: UserUseCase) : ViewModel() {
    private val state: MutableLiveData<UserState> = MutableLiveData()
    val viewState: LiveData<UserState> = state

    fun getContacts() {
        viewModelScope.launch {
            val response = useCase.getContacts()
            if (response.isSuccess) {
                state.value = UserState.SuccessApi(response.data)
            } else {
                state.value = UserState.ErrorApi(response.messageError)
            }
        }
    }
}
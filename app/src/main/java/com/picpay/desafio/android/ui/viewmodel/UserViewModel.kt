package com.picpay.desafio.android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.ui.viewmodel.state.UserViewModelState
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val stateLiveData = MutableLiveData<UserViewModelState>().apply {
        value = UserViewModelState.Loading
    }

    fun getState(): LiveData<UserViewModelState> = stateLiveData

    fun getAllUsers() {
        viewModelScope.launch {
            stateLiveData.postValue(UserViewModelState.Loading)
            try {

                val users = repository.getAllUser()

                if (users.isEmpty()) {
                    stateLiveData.postValue(UserViewModelState.Error)
                } else {
                    stateLiveData.postValue(UserViewModelState.Success(users))
                }
            } catch (exception: Throwable) {
                Log.e("Teste", "Erro inesperado", exception)
                stateLiveData.postValue(UserViewModelState.Error)
            }
        }
    }

}
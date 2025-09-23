package com.picpay.desafio.android.presentation.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

sealed class UsersState {
    object Loading : UsersState()
    data class Success(val users: List<User>) : UsersState()
    data class Error(val message: String) : UsersState()
}

class UsersViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    private val _state = MutableLiveData<UsersState>()
    val state: LiveData<UsersState> get() = _state

    fun loadUsers() {
        _state.value = UsersState.Loading
        viewModelScope.launch {
            try {
                val users = getUsersUseCase()
                _state.value = UsersState.Success(users)
            } catch (e: Exception) {
                _state.value = UsersState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}

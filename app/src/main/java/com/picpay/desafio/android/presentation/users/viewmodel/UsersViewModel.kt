package com.picpay.desafio.android.presentation.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.launch

sealed class UsersState {
    object Loading : UsersState()
    data class Success(val users: List<User>) : UsersState()
    data class Error(val message: String) : UsersState()
}

class UsersViewModel : ViewModel() {
    private val _state = MutableLiveData<UsersState>()
    val state: LiveData<UsersState> get() = _state

    fun loadUsers() {
        _state.value = UsersState.Loading
        viewModelScope.launch {
            try {
                // TODO: Implementar chamada da API através de UseCase/Repository
                // Por enquanto, simulando uma chamada bem-sucedida vazia
                _state.value = UsersState.Success(emptyList())
            } catch (e: Exception) {
                _state.value = UsersState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}

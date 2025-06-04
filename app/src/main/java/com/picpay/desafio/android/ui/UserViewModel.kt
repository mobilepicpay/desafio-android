package com.picpay.desafio.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.UiState
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.refreshUsers()

                repository.users
                    .catch { error ->
                        _uiState.value = UiState.Error(error.message ?: "Erro")
                    }
                    .collectLatest { users ->
                        _uiState.value = UiState.Success(users)
                    }

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erro inesperado")
            }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.refreshUsers()
                repository.users.collectLatest { users ->
                    _uiState.value = UiState.Success(users)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erro")
            }
        }
    }

}

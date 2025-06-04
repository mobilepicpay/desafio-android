package com.picpay.desafio.android.domain.model

sealed class UiState {
    data object Loading : UiState()
    data class Success(val users: List<User>) : UiState()
    data class Error(val message: String? = null) : UiState()
}

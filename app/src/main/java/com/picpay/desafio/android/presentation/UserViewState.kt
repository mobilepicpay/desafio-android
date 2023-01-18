package com.picpay.desafio.android.presentation

import com.picpay.desafio.android.core.DataError
import com.picpay.desafio.android.domain.model.User

sealed class UserViewState {
    data class Success(val list: List<User>) : UserViewState()
    data class Error(val error: DataError) : UserViewState()
    object Loading : UserViewState()
}

package com.picpay.desafio.android.user.viewmodel.state

import com.picpay.desafio.android.user.model.User

sealed class UserState {
    data class SuccessApi(val users: List<User>) : UserState()
    data class ErrorApi(val message: String) : UserState()
}
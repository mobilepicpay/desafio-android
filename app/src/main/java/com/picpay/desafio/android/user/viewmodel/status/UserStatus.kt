package com.picpay.desafio.android.user.viewmodel.status

import com.picpay.desafio.android.user.model.User

sealed class UserStatus {
    data class UserError(val error: Throwable) : UserStatus()
    data class UserSuccess(val users: List<User>) : UserStatus()
}
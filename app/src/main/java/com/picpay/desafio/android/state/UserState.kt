package com.picpay.desafio.android.state

import com.picpay.desafio.android.domain.entities.User

sealed class UserState {
    data class ShowLoading(val show: Boolean) : UserState()
    data class ShowUserList(val userList: List<User>) : UserState()
    data class Error(val error: String?) : UserState()
}
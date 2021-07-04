package com.picpay.desafio.android.state

import com.picpay.desafio.android.domain.entities.User

sealed class UserState {
    class ShowLoading(val show: Boolean) : UserState()
    class ShowUserList(val userList: List<User>) : UserState()
    object Error : UserState()
}
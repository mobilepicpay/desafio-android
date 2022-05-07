package com.picpay.desafio.android.presentation.userlist

import com.picpay.desafio.android.presentation.model.UserPresentable

internal sealed class UserListState {
    object Loading : UserListState()
    data class Ready(val users: List<UserPresentable>) : UserListState()
    object Error : UserListState()
}

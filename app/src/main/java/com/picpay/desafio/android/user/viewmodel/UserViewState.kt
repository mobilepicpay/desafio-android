package com.picpay.desafio.android.user.viewmodel

import com.picpay.desafio.android.user.domain.UserDomain

data class UserViewState(
    val users: List<UserDomain>? = null,
    val isLoading: Boolean = true
) {

    fun postSuccess(newUsers: List<UserDomain>) = copy(
        users = newUsers,
        isLoading = false
    )
}
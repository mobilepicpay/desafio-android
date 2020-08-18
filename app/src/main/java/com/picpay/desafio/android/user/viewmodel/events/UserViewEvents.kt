package com.picpay.desafio.android.user.viewmodel.events

sealed class UserViewEvents {
    data class UserViewShowLoading(val visible: Int) : UserViewEvents()
}
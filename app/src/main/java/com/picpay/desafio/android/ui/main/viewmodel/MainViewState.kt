package com.picpay.desafio.android.ui.main.viewmodel

import com.picpay.desafio.android.data.model.User

data class MainViewState(
    val users: List<User>? = null,
    val error: String? = null,
    val isLoading: Boolean = true
) {

    fun postSuccess(users: List<User>?): MainViewState {
        return copy(
            users = users,
            error = null,
            isLoading = false
        )
    }

    fun postError(error: String?): MainViewState {
        return copy(
            users = null,
            error = error,
            isLoading = false
        )
    }
}

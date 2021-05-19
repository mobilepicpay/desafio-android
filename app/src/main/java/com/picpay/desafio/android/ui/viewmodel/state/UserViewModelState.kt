package com.picpay.desafio.android.ui.viewmodel.state

import com.picpay.desafio.android.domain.User

sealed class UserViewModelState {

    //possiveis estados para a tela
    object  Loading : UserViewModelState()

    data class Success(val users: List<User>) : UserViewModelState()

    object Error: UserViewModelState()


}

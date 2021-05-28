package com.picpay.desafio.android.ui.viewmodel.state

import android.view.ViewConfiguration.get
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.ui.viewmodel.UserViewModel

sealed class UserViewModelState {

    // Object gera uma instância unica, e não precisa passar parametro no construtor

    //possiveis estados para a tela
    object Loading : UserViewModelState()

    data class Success(val users: List<User>) : UserViewModelState()

    object Error : UserViewModelState()


}

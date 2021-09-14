package com.picpay.desafio.android.interactor

import com.picpay.desafio.android.data.User

object PicPayState {

    sealed class GetUsers {

        object Empty : GetUsers()

        data class Data(
            val users: List<User>
        ) : GetUsers()

        data class Error(
            val exception: Exception
        ) : GetUsers()
    }
}

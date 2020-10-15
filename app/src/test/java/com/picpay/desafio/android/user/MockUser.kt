package com.picpay.desafio.android.user

import com.picpay.desafio.android.user.model.ResultUser
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.repository.local.UserEntity
import java.util.*

val mockListUser: ArrayList<User> = arrayListOf(
    User(
        1,
        "x",
        "nome usuario",
        "nomeusuario"
    )
)

val mockListUserEntity: ArrayList<UserEntity> = arrayListOf(
    UserEntity(
        1,
        "x",
        "nome usuario",
        "nomeusuario"
    )
)

val listContactsSuccess: ResultUser<List<User>> = ResultUser(
    data = mockListUser
)

val listContactsNotSuccess: ResultUser<List<User>> = ResultUser(
    data = arrayListOf(),
    isSuccess = false,
    messageError = "Não foi possível concluir sua solicitação no momento"
)
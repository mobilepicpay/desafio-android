package com.picpay.desafio.android

import com.picpay.desafio.android.data.entity.UserEntity
import com.picpay.desafio.android.domain.model.User

object UsersStub {
    val listUsersEntity = listOf(
        UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1L),
        UserEntity(img = "img 2", name = "name 2", username = "user name 2", id = 2L)
    )
    val listUsers = listOf(
        User(img = "img 1", name = "name 1", username = "user name 1"),
        User(img = "img 2", name = "name 2", username = "user name 2")
    )

    val user = User(img = "img 1", name = "name 1", username = "user name 1")

    val userEntity = UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1L)
}

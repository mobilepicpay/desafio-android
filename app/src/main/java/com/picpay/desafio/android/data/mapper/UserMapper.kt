package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.dto.UserResponse
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.domain.model.User

object UserMapper {

    private fun toUserModel(db: UserDb) =
        User(
            name = db.name,
            id = db.id,
            img = db.img,
            username = db.username
        )

    private fun toUserDb(response: UserResponse) =
        UserDb(
            id = response.id,
            name = response.name,
            img = response.img,
            username = response.username
        )

    fun toUsersDb(response: List<UserResponse>): List<UserDb> =
        response.map { toUserDb(it) }

    fun toUsersModel(dbUsers: List<UserDb>): List<User> =
        dbUsers.map { toUserModel(it) }

}
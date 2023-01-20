package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.core.BaseMapper
import com.picpay.desafio.android.data.entity.UserEntity
import com.picpay.desafio.android.domain.model.User

object UserMapper : BaseMapper<UserEntity, User>() {
    override fun transformTo(source: UserEntity): User =
        User(
            username = source.username ?: "",
            name = source.name ?: "",
            img = source.img ?: ""
        )
}

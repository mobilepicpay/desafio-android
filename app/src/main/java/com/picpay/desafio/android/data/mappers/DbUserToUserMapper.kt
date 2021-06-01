package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.DbUser
import com.picpay.desafio.android.domain.User

class DbUserToUserMapper {

    fun convertDbUserToUser(dbUser: DbUser): User = User(
        id = dbUser.id,
        name = dbUser.name,
        imgUrl = dbUser.imgUrl,
        userName = dbUser.userName
    )

}

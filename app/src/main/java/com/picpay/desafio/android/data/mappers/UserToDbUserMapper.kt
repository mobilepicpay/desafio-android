package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.DbUser
import com.picpay.desafio.android.domain.User

class UserToDbUserMapper {

    fun convertUserToDbUser(user: User): DbUser = DbUser(
        id = user.id,
        name = user.name,
        imgUrl = user.imgUrl,
        userName = user.userName
    )

}

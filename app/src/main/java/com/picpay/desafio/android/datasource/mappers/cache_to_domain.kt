package com.picpay.desafio.android.datasource.mappers

import com.picpay.desafio.android.datasource.cache.model.UserCM
import com.picpay.desafio.android.domain.model.User

fun List<UserCM>.toDomain():List<User>{
    return this.map {
        it.toDomain()
    }
}

fun UserCM.toDomain():User{
    return User(
        img=this.img,
        name = this.name,
        id=this.id,
        username=this.username
    )
}
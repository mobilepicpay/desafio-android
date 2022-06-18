package com.picpay.desafio.android.datasource.mappers

import com.picpay.desafio.android.datasource.remote.model.UserRM
import com.picpay.desafio.android.domain.model.User

fun List<UserRM>.toDomain():List<User>{
    return this.map { it.toDomain() }
}

fun UserRM.toDomain():User{
    return User(
        img=this.img,
        name = this.name,
        id=this.id,
        username=this.username
    )
}
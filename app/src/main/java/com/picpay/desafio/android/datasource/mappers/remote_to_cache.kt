package com.picpay.desafio.android.datasource.mappers

import com.picpay.desafio.android.datasource.cache.model.UserCM
import com.picpay.desafio.android.datasource.remote.model.UserRM

fun List<UserRM>.toCache():List<UserCM>{
    return this.map {
        it.toCache()
    }
}

fun UserRM.toCache():UserCM{
    return UserCM(
        username = this.username,
        name = this.name,
        img = this.img,
        id = this.id
    )
}
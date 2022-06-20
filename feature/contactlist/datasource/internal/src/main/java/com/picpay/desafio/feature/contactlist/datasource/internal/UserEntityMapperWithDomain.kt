package com.picpay.desafio.feature.contactlist.datasource.internal

import com.picpay.desafio.feature.contactlist.usecase.UserDomain

class UserEntityMapperWithDomain() {
    fun mapFromDomain(userEntity: UserEntity) = UserDomain(
        img = userEntity.img,
        name = userEntity.name,
        id = userEntity.id,
        username = userEntity.username
    )

    fun mapToEntity(userDomain: UserDomain) = UserEntity(
        id = userDomain.id,
        img = userDomain.img,
        name = userDomain.name,
        username = userDomain.username
    )
}